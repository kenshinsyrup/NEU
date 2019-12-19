import os.path
import socket
import struct
import time

import table
import threading
import util

_CONFIG_UPDATE_INTERVAL_SEC = 5

_MAX_UPDATE_MSG_SIZE = 1024
_BASE_ID = 8000


def _ToPort(router_id):
    return _BASE_ID + router_id


def _ToRouterId(port):
    return port - _BASE_ID


class Router:
    def __init__(self, config_filename):
        # ForwardingTable has 3 columns (DestinationId,NextHop,Cost). It's
        # threadsafe.
        self._forwarding_table = table.ForwardingTable()
        # Config file has router_id, neighbors, and link cost to reach
        # them.
        self._config_filename = config_filename
        self._router_id = None
        # Socket used to send/recv update messages (using UDP).
        self._socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        # Remember neighbors
        self._neighbor_routers = []

        # Listening thread.
        self._receive_update_message_thread = threading.Thread(target=self._receive_update_message)

        # Sending thread.
        self._send_update_message_thread = threading.Thread(target=self._send_update_message)
        self._prev_snapshot = []

    def start(self):
        print('Router started.')
        # Start a periodic closure to update config.
        self._config_updater = util.PeriodicClosure(
            self.load_config, _CONFIG_UPDATE_INTERVAL_SEC)
        self._config_updater.start()

        time.sleep(2)

        # TODO: init and start other threads.

        # Receive update messages from neighbor routers in background.
        self._receive_update_message_thread.start()

        # Send update messages to neighbor routers in background.
        self._send_update_message_thread.start()

        while True: pass

    def _send_update_message(self):
        while True:
            time.sleep(1)

            # Send update message to neighbor routers, update message contains all the (dest_router_id, dest_cost) that
            # we(current router) could reach.
            # 1. Get current router's forwarding table.
            snapshot = self._forwarding_table.snapshot()

            # If current snapshot is different with previous shnapshot, should send update message
            has_update = False
            for (dest_router_id, next_hop, cost) in snapshot:
                find_dest = False
                for (prev_dest_router_id, prev_next_hop, prev_cost) in self._prev_snapshot:
                    if dest_router_id == prev_dest_router_id:
                        find_dest = True
                        if next_hop != prev_next_hop or cost != prev_cost:
                            has_update = True
                            break

                if not find_dest:
                    has_update = True

                if has_update:
                    break

            # If has update, need send update message to neighbor routers
            if has_update:
                # 2. Build update message for current router.
                msg = b''
                msg += struct.pack('!h', len(snapshot))
                for (dest_router_id, _, dest_cost) in snapshot:
                    msg += struct.pack('!h', int(dest_router_id))
                    msg += struct.pack('!h', int(dest_cost))


                # 3. Send update message to all routers that are reachable by current router
                for neighbor_router_id in self._neighbor_routers:
                    # port = str(8000 + int(neighbor_router_id))
                    self._socket.sendto(msg, ('127.0.0.1', _ToPort(int(neighbor_router_id))))

    def _receive_update_message(self):
        while True:
            time.sleep(1)

            # Receive update message from known routers
            # 1. Get current router's forwarding table.
            snapshot = self._forwarding_table.snapshot()

            # 2. Receive neighbor router update messages.
            msg, addr = self._socket.recvfrom(1024)
            neighbor_router_id = _ToRouterId(addr[1])

            # read neighbor_update_msg to [] of ()
            start = 0
            step = 2
            (count,) = struct.unpack('!h', msg[start: start + step])
            start += step
            neighbor_update_msg = []
            for i in range(count):
                (dest_id,) = struct.unpack('!h', msg[start: start + step])
                start += step

                (cost,) = struct.unpack('!h', msg[start: start + step])
                start += step

                neighbor_update_msg.append((int(dest_id), int(cost)))

            print('Processed updated msg: ' + str(neighbor_update_msg))

            # 3. Get the least cost from current router to this neighbor router
            neighbor_router_cost = -1
            for (dest_router_id, next_hop, cost) in snapshot:
                dest_router_id = int(dest_router_id)
                next_hop = int(next_hop)
                cost = int(cost)

                if neighbor_router_id == dest_router_id:
                    neighbor_router_cost = cost
                    break

            print('Cost from current router ' + str(self._router_id) + ' to update msg router ' + str(neighbor_router_id) + ' is ' + str(
                neighbor_router_cost))
            # The neighbor router must be reachable.
            if neighbor_router_cost == -1:
                continue

            # 4. Calculate least cost path for every dest_router for current router with the neighbor update msg.
            # If we get one new least cost path, we update the corresponding (dest_router_id, next_hop, cost) tuple in
            # current router's forwarding table, where the new least cost and new next_hop(the current neighbor router)
            # applied.
            updated_snapshot = []
            has_update = False

            for (dest_router_id, next_hop, cost) in snapshot:
                dest_router_id = int(dest_router_id)
                next_hop = int(next_hop)
                cost = int(cost)
                find_new_path = False

                for (neighbor_dest_router_id, neighbor_dest_router_cost) in neighbor_update_msg:
                    # If the dest router is same
                    if neighbor_dest_router_id == dest_router_id:
                        if neighbor_router_cost + neighbor_dest_router_cost < cost:
                            has_update = True
                            new_cost = neighbor_router_cost + neighbor_dest_router_cost
                            updated_snapshot.append((neighbor_dest_router_id, neighbor_router_id, new_cost))
                            find_new_path = True

                if not find_new_path:
                    updated_snapshot.append((dest_router_id, next_hop, cost))

            # 5. If has update, update current router's fowarding table.
            if has_update:
                self._forwarding_table.reset(updated_snapshot)
                # keep current snapshot
                self._prev_snapshot = self._forwarding_table.snapshot()

            print('After receive update message from neighbor router: ' + str(
                neighbor_router_id) + ', current snapshot is: ')
            print(self._forwarding_table.snapshot())

    def stop(self):
        if self._config_updater:
            self._config_updater.stop()

        # TODO: clean up other threads.
        if self._receive_update_message_thread:
            self._receive_update_message_thread.join()
        if self._send_update_message_thread:
            self._send_update_message_thread.join()

    def load_config(self):
        print('is file? ' + str(os.path.isfile(self._config_filename)))
        assert os.path.isfile(self._config_filename)
        with open(self._config_filename, 'r') as f:
            router_id = int(f.readline().strip())

            # Only set router_id when first initialize.
            # Listen on its router_id relevant port
            if not self._router_id:
                self._socket.bind(('localhost', _ToPort(router_id)))
                self._router_id = router_id
                print('Current router id is: ' + str(self._router_id) + '. Listening on port: ' + str(
                    _ToPort(router_id)))

            # TODO: read and update neighbor link cost info.
            # When load config, we only know our self, dest_router_id(neighbor router), dest_cost, so we set the
            # next_hop equals to dest_router_id by default, then update next_hop and dest_cost in future.
            line = f.readline()
            snapshot = []
            while line:
                # Process a neighbor router
                tokens = [x.strip() for x in line.split(',')]
                dest_router_id = tokens[0]
                next_hop = dest_router_id
                dest_cost = tokens[1]
                snapshot.append((dest_router_id, next_hop, dest_cost))

                # Remember the neighbor routers.
                if dest_router_id not in self._neighbor_routers:
                    self._neighbor_routers.append(dest_router_id)

                # Read next neighbor line.
                line = f.readline()

            self._forwarding_table.reset(snapshot)

            print('After load config, the snapshot is: ')
            print(self._forwarding_table.snapshot())
            print('Neighbor routers:')
            print(self._neighbor_routers)
