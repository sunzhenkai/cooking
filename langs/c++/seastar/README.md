# 简介

Seastar Demo。

- server：监听 10010端口，接受客户端连接请求，并打印客户端传送的数据。
- client：向 `localhost:10010` 发送数据。

# 编译

```shell
# 1. build seastar
# 2. build project
g++ -std=c++17 `pkg-config --cflags --libs /path/to/seastar/build/release/seastar.pc` -o output/client src/client.cpp 
g++ -std=c++17 `pkg-config --cflags --libs /path/to/seastar/build/release/seastar.pc` -o output/server src/server.cpp
```

