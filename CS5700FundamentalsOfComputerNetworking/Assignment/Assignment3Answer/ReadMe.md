> 1 start memcached server on terminal window

```memcached -p 8182```

> 2 start eval expression TCP server

```python3 tcp_server.py```

> 3 start HTTP server

```python3 http_server.py```

> 4 do tests on PostMan

check /status.html: GET ```127.0.0.1:8180/status.html```

check /api/gettime: GET ```127.0.0.1:8180/api/gettime```

check /api/evalexpression: Post ```127.0.0.1:8180/api/evalexpression``` with body like ```1+2```

> 5 do test with HTTP client

run ```python3 http_client.py```, then it will run some tests and output the result string on the terminal



