import socket
import struct
import threading
import utils

host_name = socket.getfqdn()
host_ip = socket.gethostbyname(host_name)
host_port = 8181

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((host_ip, host_port))
s.listen()
print('Server started. Waiting for connection...')

def handler(conn, addr):
    exprs = utils.read_expressions(conn)
    print('recv', len(exprs), 'expressions from', addr)
    ans = []
    for expr in exprs:
        print(expr)
        ans.append(str(eval(expr)).encode())
    utils.write_expressions(conn, ans)
    conn.close()


while True:
    conn, addr = s.accept()
    print('Server connected by', addr)
    threading.Thread(target=handler, args=(conn, addr)).start()
