#include <iostream>
#include "core/app-template.hh"
#include "core/distributed.hh"

using namespace std;
using namespace seastar;

class TCPServer {
private:
    app_template app;
    int32_t _port;
public:
    TCPServer (int32_t port) : _port(port) {}
    future<> start () {
        listen_options lo(true);

        return do_with(listen(socket_address(ipv4_addr(_port))), [] (auto& ss) {
            return keep_doing([&ss] {
                return ss.accept().then([] (connected_socket conn, socket_address addr) mutable {
                    auto in = conn.input();
                    return do_with(conn.input(), [] (auto& in) {
                        return in.read().then([&in] (auto buf) {
                            if (!buf.empty()) {
                                cout << "[SERVER] DATA: " << buf.get() << endl;
                            }
                            return in.close();
                        });
                    });
                });
            });
        });

    };
};

int main (int argc, char** argv) {
    app_template app;
    return app.run(argc, argv, [&] {
        TCPServer server(10010);
        return server.start();
    });
}