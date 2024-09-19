#include <iostream>
#include "core/app-template.hh"
#include "core/distributed.hh"
#include "core/sleep.hh"
#include "net/api.hh"
#include "net/socket_defs.hh"

using namespace std;
using namespace seastar;

class TCPClient {
private:
    socket_address sa;
public:
    TCPClient (string host = "127.0.0.1", int port = 10010) : sa(ipv4_addr(host, port)) {}

    future<> send() {
        return connect(sa).then([] (connected_socket conn) {
            return do_with(std::move(conn.output()), [] (auto& out) {
                return out.write("Hello World").then([&out] {
                    return out.close();
                });
            });
        });
    }
};

int main (int argc, char** argv) {
    app_template app;
    return app.run(argc, argv, [&] {
        TCPClient client;
        return client.send();
    });
}