import socket
import struct
import threading
import datetime
import utils

host_name = socket.getfqdn()
# host_ip = socket.gethostbyname(host_name)
host_ip = '127.0.0.1'
host_port = 80

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((host_ip, host_port))
s.listen()
print('Server started. Waiting for connection...')

# for status statistics
server_start_time = datetime.datetime.now()
time_evalexpression_list = []
evalexpression_list = []
time_gettime_list = []

# not exist URL, 404
def handle_client_error(conn, version):
    data = (version + ' 404 ERROR\r\n').encode('utf-8')
    conn.sendall(data)

def handle_status(conn, version):
    status = b'<h1>API count information</h1>\r\n'

    time_now = datetime.datetime.now()
    # evalexpression status
    evalexpression_last_minute = 0
    evalexpression_last_hour = 0
    evalexpression_last_day = 0
    evalexpression_life_time = 0
    for call_time in time_evalexpression_list:
        if time_now - call_time <= datetime.timedelta(minutes = 1):
            evalexpression_last_minute += 1
        if time_now - call_time <= datetime.timedelta(hours = 1):
            evalexpression_last_hour += 1
        if time_now - call_time <= datetime.timedelta(hours = 24):
            evalexpression_last_day += 1
        evalexpression_life_time += 1
    evalexpression_status = '<h3>/api/evalexpression</h3>\r\n<ul>\r\n<li>last minute: {}</li>\r\n<li>last hour: {}</li>\r\n<li>last 24 hours: {}</li>\r\n<li>lifetime: {}</li>\r\n</ul>\r\n'.format(evalexpression_last_minute, evalexpression_last_hour, evalexpression_last_day, evalexpression_life_time).encode('utf-8')

    # gettime status
    gettime_last_minute = 0
    gettime_last_hour = 0
    gettime_last_day = 0
    gettime_life_time = 0
    for call_time in time_gettime_list:
        if time_now - call_time <= datetime.timedelta(minutes = 1):
            gettime_last_minute += 1
        if time_now - call_time <= datetime.timedelta(hours = 1):
            gettime_last_hour += 1
        if time_now - call_time <= datetime.timedelta(hours = 24):
            gettime_last_day += 1
        gettime_life_time += 1
    gettime_status = '<h3>/api/gettime</h3>\r\n<ul>\r\n<li>last minute: {}</li>\r\n<li>last hour: {}</li>\r\n<li>last 24 hours: {}</li>\r\n<li>lifetime: {}</li>\r\n</ul>\r\n'.format(gettime_last_minute, gettime_last_hour, gettime_last_day, gettime_life_time).encode('utf-8')
    
    # last 10 evalexpression
    latest_evalexpression_status = '<h1>Last {} expressions</h1>\r\n'.format(len(evalexpression_list)).encode('utf-8')
    latest_evalexpression_li = b''
    for evalexpression in reversed(evalexpression_list):
        latest_evalexpression_li += '<li>{}</li>\r\n'.format(evalexpression.decode('utf-8')).encode('utf-8')
    if(latest_evalexpression_li != b''):
        latest_evalexpression_status += b'<ul>\r\n' + latest_evalexpression_li + b'</ul>\r\n'

    html_page = b'<!DOCTYPE html>\r\n<html>\r\n<body>\r\n' + evalexpression_status + gettime_status + latest_evalexpression_status + b'</body>\r\n</html>\r\n'

    conn.sendall(html_page)

# /api/gettime
def handle_gettime(conn, version):
    time_gettime_list.append(datetime.datetime.now())

    data = (version + ' 200 OK\r\n').encode('utf-8')
    data +=  b'Content-Type: text/html\r\n'

    time = datetime.datetime.now()
    data += b'Content-Length: ' + str(len(str(time))).encode('utf-8')  + b'\r\n' 
    data += b'\r\n'
    data += str(time).encode('utf-8')

    conn.sendall(data)

def handle_evalexpression(conn, version, expression):
    time_evalexpression_list.append(datetime.datetime.now())
    if(len(evalexpression_list) < 10):
        evalexpression_list.append(expression)
    else:
        evalexpression_list.pop(0)
        evalexpression_list.append(expression)

    data = version.encode('utf-8') + b' 200 OK\r\n'
    data += b'Content-Type: text/html\r\n'

    ans = str(eval(expression))
    data += (b'Content-Length: ' + str(len(ans)).encode('utf-8') + b'\r\n')
    data += b'\r\n'
    data += ans.encode('utf-8')

    conn.sendall(data)

def handler(conn, addr):
    header_body = utils.read_req(conn)

    # get the header, trim tailing \r\n\r\n
    header = header_body[0]
    header = header[0:len(header) - 4].decode('utf-8') 

    header_info = header.split(' ')
    action = header_info[0]
    api = header_info[1]
    version = header_info[2]

    if api == '/api/gettime':
        handle_gettime(conn, version)
    elif api == '/status.html':
        handle_status(conn, version)
    elif api == '/api/evalexpression':
        handle_evalexpression(conn, version, header_body[1])
    else:
        handle_client_error(conn, version)

    conn.close()

while True:
    conn, addr = s.accept()
    threading.Thread(target=handler, args=(conn, addr)).start()
