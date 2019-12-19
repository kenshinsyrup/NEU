import socket
import threading

import config
import utils

host_name = socket.getfqdn()
host_ip = config.EXPRESSION_EVAL_SERVER
host_port = config.EXPRESSION_EVAL_PORT

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((host_ip, host_port))
s.listen()
print('Server started...')


def handle_evalexpression(conn, expression):
    ans = str(eval(expression))
    utils.write_expressions(conn, [ans.encode('utf-8')])


def handler(conn, addr):
    # get body data from a post request
    req_data = utils.read_req(conn).decode('utf-8')

    # extract the expression from request
    start_idx = req_data.find('\r\n\r\n') + 4
    expression = req_data[start_idx:]

    # eval expression and write to response
    handle_evalexpression(conn, expression)

    # close socket connection
    conn.close()


while True:
    conn, addr = s.accept()
    threading.Thread(target=handler, args=(conn, addr)).start()
