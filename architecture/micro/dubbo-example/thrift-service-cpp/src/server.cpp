////
//// Created by 孙振凯 on 2019-09-07.
////
//
//#include <iostream>
//
//#include <gflags/gflags.h>
//#include <butil/logging.h>
//#include <brpc/server.h>
//#include <brpc/thrift_service.h>
//#include <brpc/channel.h>
//
//#include "User_types.h"
//#include "User_types.cpp"
//
//using namespace demos::user;
//
//class EchoServiceImpl : public brpc::ThriftService {
//public:
//    void ProcessThriftFramedRequest(brpc::Controller* cntl,
//                                    brpc::ThriftFramedMessage* req,
//                                    brpc::ThriftFramedMessage* res,
//                                    google::protobuf::Closure* done) override {
//        // Dispatch calls to different methods
//        if (cntl->thrift_method_name() == "Echo") {
//            return Echo(cntl, req->Cast<UserServiceRequest>(),
//                        res->Cast<UserServiceResponse>(), done);
//        } else {
//            cntl->SetFailed(brpc::ENOMETHOD, "Fail to find method=%s",
//                            cntl->thrift_method_name().c_str());
//            done->Run();
//        }
//    }
//
//    void Echo(brpc::Controller* cntl,
//              const UserServiceRequest* req,
//              UserServiceResponse* res,
//              google::protobuf::Closure* done) {
//        // This object helps you to call done->Run() in RAII style. If you need
//        // to process the request asynchronously, pass done_guard.release().
//        brpc::ClosureGuard done_guard(done);
//
//        res->isOk = true;
//    }
//};
//
//int main() {
//    LOG(INFO) << "begin to run server ...";
//
//    brpc::Server server;
//    brpc::ServerOptions options;
//
//    options.thrift_service = new EchoServiceImpl();
//    options.idle_timeout_sec = -1;
//    options.max_concurrency = 0;
//
//    // Start the server.
//    if (server.Start(8019, &options) != 0) {
//        LOG(ERROR) << "Fail to start EchoServer";
//        return -1;
//    }
//
//    // Wait until Ctrl-C is pressed, then Stop() and Join() the server.
//    server.RunUntilAskedToQuit();
//    return 0;
//}