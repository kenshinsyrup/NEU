import collections
import datetime
import struct
import time

import config
import udt


# Go-Back-N reliable transport protocol.
class GoBackN:
    # "msg_handler" is used to deliver messages to application layer
    # when it's ready.
    def __init__(self, local_port, remote_port, msg_handler):
        self.network_layer = udt.NetworkLayer(local_port, remote_port, self)
        self.msg_handler = msg_handler

        self.start_time = datetime.datetime.now()
        self.base = 1
        self.nextseqnum = 1

        # sndpkts in window
        self.sndpkts = collections.deque()

        # init self.ack_pkts
        self.ack_pkts = []
        for i in range(config.WINDOW_SIZE):
            self.ack_pkts.append(None)

        # the very first 0 ack:
        # calculate send check sum
        self.receiver_wait_for_seq_num = 1
        checksum = 0
        checksum += config.MSG_TYPE_ACK
        checksum += (self.receiver_wait_for_seq_num - 1)
        # keep checksum in range [-32767, 32767]
        checksum = checksum % 32767

        # build and send pkt
        ack_pkt = struct.pack('!h', config.MSG_TYPE_ACK)
        ack_pkt += struct.pack('!h', self.receiver_wait_for_seq_num - 1)
        ack_pkt += struct.pack('!h', checksum)
        self.ack_pkts[0] = ack_pkt
        self.ack_pkt = ack_pkt
        self.previous_ack_seq_num = 0

    # "send" is called by application. Return true on success, false
    # otherwise.
    def send(self, msg):
        # if window is not full
        if self.nextseqnum < self.base + config.WINDOW_SIZE:
            # calculate send check sum
            checksum = 0
            for x in list(msg):
                checksum += x
            checksum += config.MSG_TYPE_DATA
            checksum += self.nextseqnum
            # keep checksum in range [-32767, 32767]
            checksum = checksum % 32767

            # build and send pkt
            sndpkt = struct.pack('!h', config.MSG_TYPE_DATA)
            sndpkt += struct.pack('!h', self.nextseqnum)
            sndpkt += struct.pack('!h', checksum)
            sndpkt += msg
            # put into tail of window
            self.sndpkts.append(sndpkt)

            # udt send
            self.network_layer.send(sndpkt)

            if self.base == self.nextseqnum:
                self.start_time = datetime.datetime.now()

            self.nextseqnum += 1

        # if window ful, refuse to send more until have more space in window
        if self.nextseqnum >= self.base + config.WINDOW_SIZE:
            while len(self.sndpkts) > 0:
                time.sleep(1)
                # if out of time, send all pkts in window
                if (datetime.datetime.now() - self.start_time).seconds > 1:
                    self.start_time = datetime.datetime.now()
                    sndpkts = collections.deque()
                    for item in self.sndpkts:
                        sndpkts.append(item)
                    for item in sndpkts:
                        self.network_layer.send(item)

        return True

    # "handler" to be called by network layer when packet is ready.
    def handle_arrival_msg(self):
        msg = self.network_layer.recv()
        (recv_data_type,) = struct.unpack('!h', msg[0:2])
        (recv_seq_num,) = struct.unpack('!h', msg[2:4])
        (recv_checksum,) = struct.unpack('!h', msg[4:6])
        data = msg[6:]

        # caculate check sum
        checksum = 0
        for x in list(data):
            checksum += x
        checksum += recv_data_type
        checksum += recv_seq_num
        # keep checksum in range [-32767, 32767]
        checksum = checksum % 32767

        # if type is Data, means this is receive side handling arrival msg
        if recv_data_type == config.MSG_TYPE_DATA:

            # if self.receiver_wait_for_seq_num == recv_seq_num and checksum == recv_checksum and self.previous_ack_seq_num + 1 == self.receiver_wait_for_seq_num:
            if self.receiver_wait_for_seq_num <= recv_seq_num < self.receiver_wait_for_seq_num + config.WINDOW_SIZE and checksum == recv_checksum:
                i = recv_seq_num - self.receiver_wait_for_seq_num

                # calculate ack checksum
                ack_checksum = 0
                ack_checksum += config.MSG_TYPE_ACK
                ack_checksum += recv_seq_num
                # keep ack_checksum in range [-32767, 32767]
                ack_checksum = ack_checksum % 32767

                # build ack
                ack_pkt = struct.pack('!h', config.MSG_TYPE_ACK)
                ack_pkt += struct.pack('!h', recv_seq_num)
                ack_pkt += struct.pack('!h', ack_checksum)

                if 0 <= i < config.WINDOW_SIZE:
                    self.ack_pkts[i] = ack_pkt

                # if received data hsa correct seq num and data not corrupt, send back ACK to sender
                if self.previous_ack_seq_num + 1 == self.receiver_wait_for_seq_num and recv_seq_num == self.receiver_wait_for_seq_num:
                    # deliver data
                    self.msg_handler(data)

                    # send ack
                    self.network_layer.send(ack_pkt)
                    self.ack_pkt = ack_pkt
                    self.previous_ack_seq_num = self.receiver_wait_for_seq_num
                    # next expected
                    self.receiver_wait_for_seq_num += 1

            # otherwise, resend previous ACKs
            else:
                for item in self.ack_pkts:
                    if item is None:
                        break
                    self.network_layer.send(item)

        # otherwise type is ACK, means this is send side handling arrival msg
        elif recv_data_type == config.MSG_TYPE_ACK:
            # if not corrupt
            if checksum == recv_checksum:
                if recv_seq_num == self.base:
                    # remove the acked pkt from window
                    self.sndpkts.popleft()
                    # move forward base ptr
                    self.base = recv_seq_num + 1
                    if self.base != self.nextseqnum:
                        self.start_time = datetime.datetime.now()
            else:
                # if window ful, refuse to send more until have more space in window
                while len(self.sndpkts) > 0:
                    time.sleep(1)
                    # if out of time, send all pkts in window
                    if (datetime.datetime.now() - self.start_time).seconds > 1:
                        self.start_time = datetime.datetime.now()
                        sndpkts = collections.deque()
                        for item in self.sndpkts:
                            sndpkts.append(item)
                        for item in sndpkts:
                            self.network_layer.send(item)

    # Cleanup resources.
    def shutdown(self):
        # TODO: cleanup anything else you may have when implementing this
        # class.
        self.network_layer.shutdown()
