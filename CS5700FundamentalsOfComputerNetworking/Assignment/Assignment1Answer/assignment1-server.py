import socket
import threading
import time
import struct

# Get host name, IP address, and port number.
#
# API: getfqdn()
#   returns a fully qualified domain name.
host_name = socket.getfqdn()
print('hostname is', host_name)

# API: gethostbyname(hostname)
#   translate a host name to IPv4 address format, and return it as a
#   string.
host_ip = socket.gethostbyname(host_name)
print('host IP address is', host_ip)
host_port = 8181

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
s.bind((host_ip, host_port))
s.listen()
print('Server started. Waiting for connection...')

# get ctime
def now():
    return time.ctime(time.time())

# buf size no more than 16
bufsize = 16

# handle a socket connection
def handler(conn, addr):
    print('Built a connection with a client, receiving data...')

    # receive msg from client to data until have received all
    data = b''
    while True:
        # receive current at most bufsize data
        curData = conn.recv(bufsize)
        if not curData: break

        # append curData to data
        data += curData
        # get total expression num
        totalNum = struct.unpack('!h', data[0:2])[0]
        if totalNum == 0: break
        
        # check whether we have received all expression msg from client
        idx = 2
        indicateLen = 2
        expressionLen = 0
        count = 0
        while(idx + indicateLen < len(data)):
            expressionLen = struct.unpack('!h', data[idx:idx+indicateLen])[0]
            count += 1
            idx += (indicateLen + expressionLen)

        # if the count of expression equals to the total expression num the client said, and have received all expressions, stop recv()
        if(count == totalNum and idx == len(data)): break
   
    print('All msg data from client is received, processing data...')

    # process the data
    idx = 0
    indicateLen = 2
    # total expression num
    expressionsNum = struct.unpack('!h', data[idx:indicateLen])[0]
    # response to client
    res = struct.pack('!h', expressionsNum)
    # res list for print
    resList =[]
    resList.append(expressionsNum)
    # received msg list for print
    receivedMsgList = []
    receivedMsgList.append(expressionsNum)
    idx += indicateLen
    while idx < len(data):
        # current expression length
        expressionLen = 0
        for val in struct.unpack('!h', data[idx: idx + indicateLen]):
            expressionLen *= 10
            expressionLen += val
        
        # append expressionLen to receivedMsgList
        receivedMsgList.append(expressionLen)

        # current expression
        idx += indicateLen
        # expression = str(data[idx: idx + expressionLen].)
        expression = data[idx: idx + expressionLen].decode('utf-8')
        # append expression to receivedMsgList
        receivedMsgList.append(expression)

        # get the eval ans of the expression
        ans = str(calculate(expression))
        res += struct.pack('!h', len(ans)) + ans.encode('utf-8')
        resList.append(len(ans))
        resList.append(ans)

        idx += expressionLen

    print('Received msg is: ', receivedMsgList)
    print('Answered msg is: ', resList)

    conn.sendall(res)

    conn.close()

# act as eval()
def calculate(s):
        operand = 0
        res = 0 # For the on-going result
        sign = 1 # 1 means positive, -1 means negative  

        for ch in s:
            if ch.isdigit():
                # Forming operand, since it could be more than one digit
                operand = (operand * 10) + int(ch)

            elif ch == '+':
                # Evaluate the expression to the left,
                # with result, sign, operand
                res += sign * operand
                # Save the recently encountered '+' sign
                sign = 1
                # Reset operand
                operand = 0

            elif ch == '-':
                res += sign * operand
                sign = -1
                operand = 0

        return res + sign * operand

# main thread
while True:
    conn, addr = s.accept()
    print('Server connected by', addr, 'at', now())
    threading.Thread(target=handler, args=(conn, addr)).start()