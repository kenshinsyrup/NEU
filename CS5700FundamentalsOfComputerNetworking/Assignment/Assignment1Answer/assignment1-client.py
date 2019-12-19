# This example is using Python 3.
import socket
import struct
import sys

# Specify server name and port number to connect to.
#
# API: getfqdn()
#   returns a fully qualified domain name.
server_name = socket.getfqdn()
print('Hostname: ', server_name)
server_port = 8181

# Make a TCP socket object.
#
# API: socket(address_family, socket_type)
#
# Address family
#   AF_INET: IPv4
#   AF_INET6: IPv6
#
# Socket type
#   SOCK_STREAM: TCP socket
#   SOCK_DGRAM: UDP socket
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect to server machine and port.
#
# API: connect(address)
#   connect to a remote socket at the given address.
s.connect((server_name, server_port))
print('Connected to server ', server_name)

# build the msg for sending
# test case1: by TA
# messages = [2,4,"3+12",6,"1+12+3"]
# test case2: by TA
# messages = [2,1,"1",3,"1-1"]
# test case3: len of msg equals to server's bufsize 16
messages = [2, 5, '3+2+1', 5, '4-1-1']
print('The problem msg is: ', messages)

msg = b''
for val in messages:
    if(isinstance(val, int)):
        msg += struct.pack('!h', val)
    else:
        msg += val.encode('utf-8')

# send msg to server
s.sendall(msg)

print('Waiting for answer...')

# buf size no more than 16
bufsize = 16

# receive answer to data until have received all
data = b''
while True:
        # receive current at most bufsize data
        curDate = s.recv(bufsize)
        if not curDate: break

        # append to data
        data += curDate
        totalNum = struct.unpack('!h', data[0:2])[0]
        if totalNum == 0: break
        
        # every loop, we check the whole data to see whether we have received all data from server
        idx = 2
        indicateLen = 2
        expressionLen = 0
        count = 0
        while(idx + indicateLen < len(data)):
            expressionLen = struct.unpack('!h', data[idx:idx+indicateLen])[0]
            count += 1

            idx += (indicateLen + expressionLen)

        # if the count we have received expression equals to the total expression num that server said, and have received all expressions, stop recv()
        if(count == totalNum and idx == len(data)): break

# process the received data
# answer list
ansList =[]
idx = 0
indicateLen = 2
expressionsNum = struct.unpack('!h', data[idx:indicateLen])[0]
ansList.append(expressionsNum)

# process each expression with expressionLen
idx += indicateLen
while idx < len(data):
    # current expression length
    expressionLen = 0
    for val in struct.unpack('!h', data[idx: idx + indicateLen]):
        expressionLen *= 10
        expressionLen += val
    
    # append expressionLen to ansList
    ansList.append(expressionLen)
    
    idx += indicateLen

    # expression
    expression = data[idx: idx + expressionLen].decode('utf-8')
    # append expression to ansList
    ansList.append(expression)
    
    idx += expressionLen

print('The answer msg is: ', ansList)

# Close socket to send EOF to server.
s.close()