import http.client

import config

conn = http.client.HTTPConnection(config.WEB_API_SERVER, config.WEB_API_PORT)

# getstatus
conn.request('GET', '/status.html')
r = conn.getresponse()
data = r.read(1024)
print(data)

# gettime
conn.request('GET', '/api/gettime')
r = conn.getresponse()
data = r.read(1024)
print(data)

# getstatus
conn.request('GET', '/status.html')
r = conn.getresponse()
data = r.read(1024)
print(data)

# evalexpression
conn.request('POST', '/api/evalexpression', body='1+2')
r = conn.getresponse()
data = r.read(1024)
print(data)

# getstatus
conn.request('GET', '/status.html')
r = conn.getresponse()
data = r.read(1024)
print(data)
