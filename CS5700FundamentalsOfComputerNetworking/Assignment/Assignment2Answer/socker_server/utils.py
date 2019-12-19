import struct

MAX_BUFFER_SIZE = 16

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
    header = bytearray()
    while len(header) < 4 or header[len(header) - 4: len(header)] != b'\r\n\r\n':
        d = conn.recv(MAX_BUFFER_SIZE)
        header += d

    # check header, if GET return header, if POST, continue receive body
    if header[0:3] == b'GET':
        return [header]

    i = 0
    while i < len(header):
        if header[i: i + 15] == b'Content-Length:':
            break
        i += 1
    
    content_len = int(header[i+16: len(header) - 4].decode('utf-8'))
    body = read_n_bytes(conn, content_len)
    # only need the first line of header in POST request
    header = header[0: i]

    return [header, body]


def write_data(conn, data):
    conn.sendall(struct.pack('!h', len(data)))
    for elem in data:
        conn.sendall(struct.pack('!h', len(elem)))
        conn.sendall(elem)
