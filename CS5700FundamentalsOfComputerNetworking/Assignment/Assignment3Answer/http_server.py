import datetime
import http
import json
import socket
from http.server import BaseHTTPRequestHandler

import pymemcache as pymemcache

import config
import utils

# the cache_server
cache_server = pymemcache.client.base.Client((config.CACHE_SERVER, config.CACHE_PORT))


def cache_request(key):
    # cache this request
    timestamp_data = cache_server.get(key)
    if timestamp_data is None:
        timestamp_data = json.dumps([])
    timestamp_list = json.loads(timestamp_data)
    timestamp_list.append(str(datetime.datetime.now()))
    cache_server.set(key, json.dumps(timestamp_list))
    print('Key ', key, ' in memcached: ', json.loads(cache_server.get(key)))


class Handler(BaseHTTPRequestHandler):

    def bad_request(self):
        self.send_response(404)
        self.send_header("Content-type", "text/plain")
        self.end_headers()
        self.wfile.write(b'<html><head></head><body><h1>404 Not Found</h1></body></html>')

    def do_GET(self):
        time_now = datetime.datetime.now()

        # gettime
        if self.path == '/api/gettime':
            content = str(time_now).encode('utf-8')

            # cache this request
            cache_request('gettime')

        # gettime status
        elif self.path == '/status.html':
            # gettime status
            if cache_server.get('gettime') is None:
                time_gettime_list = []
            else:
                time_gettime_list = json.loads(cache_server.get('gettime'))

            gettime_last_minute = 0
            gettime_last_hour = 0
            gettime_last_day = 0
            gettime_life_time = 0
            for call_time_str in time_gettime_list:
                call_time = datetime.datetime.strptime(call_time_str, '%Y-%m-%d %H:%M:%S.%f')
                if time_now - call_time <= datetime.timedelta(minutes=1):
                    gettime_last_minute += 1
                if time_now - call_time <= datetime.timedelta(hours=1):
                    gettime_last_hour += 1
                if time_now - call_time <= datetime.timedelta(hours=24):
                    gettime_last_day += 1
                gettime_life_time += 1
            gettime_status = '<h3>/api/gettime</h3>\r\n<ul>\r\n<li>last minute: {}</li>\r\n<li>last hour: {}</li>\r\n<li>last 24 hours: {}</li>\r\n<li>lifetime: {}</li>\r\n</ul>\r\n'.format(
                gettime_last_minute, gettime_last_hour, gettime_last_day, gettime_life_time).encode('utf-8')

            # evalexpression status
            if cache_server.get('evalexpression') is None:
                time_evalexpression_list = []
            else:
                time_evalexpression_list = json.loads(cache_server.get('evalexpression'))

            evalexpression_last_minute = 0
            evalexpression_last_hour = 0
            evalexpression_last_day = 0
            evalexpression_life_time = 0
            for call_time_str in time_evalexpression_list:
                call_time = datetime.datetime.strptime(call_time_str, '%Y-%m-%d %H:%M:%S.%f')
                if time_now - call_time <= datetime.timedelta(minutes=1):
                    evalexpression_last_minute += 1
                if time_now - call_time <= datetime.timedelta(hours=1):
                    evalexpression_last_hour += 1
                if time_now - call_time <= datetime.timedelta(hours=24):
                    evalexpression_last_day += 1
                evalexpression_life_time += 1
            evalexpression_status = '<h3>/api/evalexpression</h3>\r\n<ul>\r\n<li>last minute: {}</li>\r\n<li>last hour: {}</li>\r\n<li>last 24 hours: {}</li>\r\n<li>lifetime: {}</li>\r\n</ul>\r\n'.format(
                evalexpression_last_minute, evalexpression_last_hour, evalexpression_last_day,
                evalexpression_life_time).encode('utf-8')

            # latest 10 evalexpression
            if cache_server.get('expressions') is None:
                evalexpression_list = []
            else:
                evalexpression_list = json.loads(cache_server.get('expressions'))

            latest_evalexpression_status = '<h1>Last {} expressions</h1>\r\n'.format(len(evalexpression_list)).encode(
                'utf-8')
            latest_evalexpression_li = b''
            for evalexpression in reversed(evalexpression_list):
                latest_evalexpression_li += '<li>{}</li>\r\n'.format(evalexpression).encode('utf-8')
            if (latest_evalexpression_li != b''):
                latest_evalexpression_status += b'<ul>\r\n' + latest_evalexpression_li + b'</ul>\r\n'

            content = b'<!DOCTYPE html>\r\n<html>\r\n<body>\r\n' + evalexpression_status + gettime_status + latest_evalexpression_status + b'</body>\r\n</html>\r\n'

        # bad request
        else:
            self.bad_request()
            return

        self.send_response(200)
        self.send_header("Content-type", "text/plain")
        self.end_headers()
        self.wfile.write(content)

    def do_POST(self):
        if self.path != '/api/evalexpression':
            self.bad_request()
            return

        eval_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        eval_socket.connect((config.EXPRESSION_EVAL_SERVER, config.EXPRESSION_EVAL_PORT))

        headers = self.headers
        content_length = int(headers['Content-Length'])
        post_body = self.rfile.read(content_length)
        post_body_str = post_body.decode('utf-8')

        # check if the expression in body is valid
        valid = True
        for ch in post_body_str:
            if not (('0' <= ch <= '9') or ch == '-' or ch == '+'):
                valid = False
                break
        if not valid:
            self.bad_request()
            return

        # build the data and send it to Expression TCP server
        content = 'POST /api/evalexpression HTTP/1.0\r\n'
        content += 'Content-Length: ' + str(content_length) + '\r\n'
        content += '\r\n'
        content += post_body_str
        eval_socket.sendall(content.encode('utf-8'))
        # get the answer for Expression TCP server
        ans = utils.read_expressions(eval_socket)[0]
        # close the socket connection to Expression TCP server
        eval_socket.close()

        # write response answer to client
        self.send_response(200)
        self.send_header("Content-type", "text/plain")
        self.end_headers()
        self.wfile.write(ans)

        # cache this request and expression
        cache_request('evalexpression')
        expressions_data = cache_server.get('expressions')
        if expressions_data is None:
            expressions_data = json.dumps([])
        expressions_list = json.loads(expressions_data)
        if len(expressions_list) >= 10:
            expressions_list.pop(0)
        expressions_list.append(post_body_str)
        cache_server.set('expressions', json.dumps(expressions_list))
        print('Expressions list in memcached: ', json.loads(cache_server.get('expressions')))


s = http.server.ThreadingHTTPServer((config.WEB_API_SERVER, config.WEB_API_PORT), Handler)
s.serve_forever()
