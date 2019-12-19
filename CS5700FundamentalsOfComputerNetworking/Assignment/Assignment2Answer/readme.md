### How to run

> Notice: Since in ths assignment we assume the inputs are all valid, so there's no useless spaces or \r\n or anything in our input.

>1. In the location of ```server.py```, run command ```sudo python3 server.py```. You may need to input your password of current machine. The server will be running on the ```127.0.0.1:80```.

```
sudo python3 server.py
Password:
host_name:  kenshinmbp.local host_ip:  127.0.0.1  host_port:  80
Server started. Waiting for connection...
```

>2. Open another terminal window, run command ```telnet 127.0.0.1 80```.

```
$ telnet 127.0.0.1 80
Trying 127.0.0.1...
Connected to localhost.
Escape character is '^]'.
```

>3. Now you're able to input HTTP request in the telnet interactive window.

>>3.1 ```/api/gettime```

Request:
```
GET /api/gettime HTTP/1.1

```

Response:
```
HTTP/1.1 200 OK
Content-Type: text/html
Content-Length: 26

2019-10-07 10:57:10.928451Connection closed by foreign host.
```

>>3.2 ```/api/evalexpression/```

Request:
```
POST /api/evalexpression HTTP/1.0
Content-Length: 8

7+9-11+6
```

Response:
```
HTTP/1 200 OK
Content-Type: text/html
Content-Length: 2

11Connection closed by foreign host.
```

>>3.3 any invalid URL

Request:
```
GET /api/notexist HTTP/1.1

```

Response:
```
HTTP/1.1 404 ERROR
Connection closed by foreign host.
```

>>3.4 ```/status.html```, after you do each of the requests above once.

Request:
```
GET /status.html HTTP/1.1

```

Response:
```
<!DOCTYPE html>
<html>
<body>
<h3>/api/evalexpression</h3>
<ul>
<li>last minute: 0</li>
<li>last hour: 1</li>
<li>last 24 hours: 1</li>
<li>lifetime: 1</li>
</ul>
<h3>/api/gettime</h3>
<ul>
<li>last minute: 0</li>
<li>last hour: 1</li>
<li>last 24 hours: 1</li>
<li>lifetime: 1</li>
</ul>
<h1>Last 1 expressions</h1>
<ul>
<li>7+9-11+6</li>
</ul>
</body>
</html>
Connection closed by foreign host.
```
