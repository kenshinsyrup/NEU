import datetime
import struct

import config
import udt

# need Ctrl+C to terminate the receiver terminal after sender is done, otherwise the last part of file may lost.

# Stop-And-Wait reliable transport protocol.
class StopAndWait:
    # "msg_handler" is used to deliver messages to application layer

    # when it's ready.
    def __init__(self, local_port, remote_port, msg_handler):
        self.network_layer = udt.NetworkLayer(local_port, remote_port, self)
        self.msg_handler = msg_handler

        # inits all variables:
        # sender side
        self.start_time = datetime.datetime.now()
        self.sender_send_seq_num = 0
        self.ack_wait_for_seq_num = 0
        self.sndpkt = b''
        # receiver side
        self.receiver_wait_for_seq_num = 0
        self.ack_pkt = b''

    # "send" is called by application. Return true on success, false otherwise.
    def send(self, msg):
        # calculate send check sum
        checksum = 0
        for x in list(msg):
            checksum += x
            if checksum > 32767:
                checksum = checksum % 32767
        checksum += config.MSG_TYPE_DATA
        checksum += self.sender_send_seq_num
        # keep checksum in range [-32767, 32767]
        if checksum > 32767:
            checksum = checksum % 32767

        # build and send pkt
        sndpkt = struct.pack('!h', config.MSG_TYPE_DATA)
        sndpkt += struct.pack('!h', self.sender_send_seq_num)
        sndpkt += struct.pack('!h', checksum)
        sndpkt += msg
        # store sndpkt for resend
        self.sndpkt = sndpkt
        # start timer
        self.start_time = datetime.datetime.now()
        # send via udt
        self.network_layer.send(self.sndpkt)

        # try to send current msg via network layer and get ACK for this sending
        current_seq_num = self.sender_send_seq_num
        while current_seq_num == self.sender_send_seq_num:
            # if out of time
            if (datetime.datetime.now() - self.start_time).microseconds > config.TIMEOUT_MSEC:
                self.network_layer.send(self.sndpkt)
                self.start_time = datetime.datetime.now()

        return True

    # "handler" to be called by network layer when packet is ready.
    def handle_arrival_msg(self):
        msg = self.network_layer.recv()
        (recv_data_type,) = struct.unpack('!h', msg[0:2])
        (recv_seq_num,) = struct.unpack('!h', msg[2:4])
        (recv_checksum,) = struct.unpack('!h', msg[4:6])
        recv_data = msg[6:]

        # calculate check sum
        checksum = 0
        for x in list(recv_data):
            checksum += x
            if checksum > 32767:
                checksum = checksum % 32767
        checksum += recv_data_type
        checksum += recv_seq_num
        # keep checksum in range [-32767, 32767]
        if checksum > 32767:
            checksum = checksum % 32767

        # if type is DATA, means this is the receiver side handling arrival msg
        if recv_data_type == config.MSG_TYPE_DATA:
            if recv_checksum == checksum and recv_seq_num == self.receiver_wait_for_seq_num:
                # deliver data
                self.msg_handler(recv_data)

                # calculate ack checksum
                ack_checksum = 0
                ack_checksum += config.MSG_TYPE_ACK
                ack_checksum += recv_seq_num
                # keep ack_checksum in range [-32767, 32767]
                if ack_checksum > 32767:
                    ack_checksum = ack_checksum % 32767

                # build ack
                ack_pkt = struct.pack('!h', config.MSG_TYPE_ACK)
                ack_pkt += struct.pack('!h', self.receiver_wait_for_seq_num)
                ack_pkt += struct.pack('!h', ack_checksum)
                # keep ack_pkt for resend
                self.ack_pkt = ack_pkt
                # send ack
                self.network_layer.send(ack_pkt)

                # flip receiver_wait_for_seq_num
                self.receiver_wait_for_seq_num = self.flip(self.receiver_wait_for_seq_num)

            else:
                # corrupt, resend last ack
                if self.ack_pkt is not None:
                    self.network_layer.send(self.ack_pkt)

        # other wise type is ACK, means this is the sender side handling the arrival msg
        else:
            if recv_checksum == checksum and recv_seq_num == self.sender_send_seq_num:
                # flip signal
                self.sender_send_seq_num = self.flip(self.sender_send_seq_num)

    # Cleanup resources.
    def shutdown(self):
        # TODO: cleanup anything else you may have when implementing this
        # class.
        self.network_layer.shutdown()

    def flip(self, seq_num):
        if seq_num == 0:
            return 1
        return 0
