import struct

MAX_BUFFER_SIZE = 16

def read_expressions(conn):
    exprs = []
    (n,) = struct.unpack('!h', read_n_bytes(conn, 2))
    for i in range(n):
        (l,) = struct.unpack('!h', read_n_bytes(conn, 2))
        exprs.append(read_n_bytes(conn, l))
    return exprs

def write_expressions(conn, exprs):
    conn.sendall(struct.pack('!h', len(exprs)))
    for expr in exprs:
        conn.sendall(struct.pack('!h', len(expr)))
        conn.sendall(expr)

def read_n_bytes(conn, n):
    data = bytearray()
    while len(data) < n:
        remain_bytes = n - len(data)
        bufsize = min(remain_bytes, MAX_BUFFER_SIZE)
        d = conn.recv(bufsize)
        data += d
    return bytes(data)


def read_data(conn):
    data = []
    (n,) = struct.unpack('!h', read_n_bytes(conn, 2))
    for i in range(n):
        (l,) = struct.unpack('!h', read_n_bytes(conn, 2))
        data.append(read_n_bytes(conn, l))
    return data


def read_req(conn):
    req_data = bytearray()
    while 1:
        d = conn.recv(MAX_BUFFER_SIZE)
        req_data += d
        req_data_str = req_data.decode('utf-8')
        # if meet '\r\n\r\n', means header section is end
        if '\r\n\r\n' in req_data_str:
            end_idx = req_data_str.find('\r\n\r\n')
            start_idx = req_data_str.find('Content-Length')
            content_length = int(req_data_str[start_idx + 15: end_idx])
            # if we have got content_length body data, end receive
            if len(req_data_str[end_idx + 4:]) == content_length:
                break
    return req_data


def write_data(conn, data):
    conn.sendall(struct.pack('!h', len(data)))
    for elem in data:
        conn.sendall(struct.pack('!h', len(elem)))
        conn.sendall(elem)
