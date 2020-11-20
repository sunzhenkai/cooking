//
// Created by 孙振凯 on 2019-09-07.
//

#include <iostream>

#include <gflags/gflags.h>
//#include <butil/logging.h>
//#include <brpc/server.h>
//#include <brpc/thrift_service.h>
//#include <brpc/channel.h>
//#include <brpc/policy/consul_naming_service.h>
//#include <brpc/details/naming_service_thread.h>
//
//#include "User_types.h"
//#include "User_types.cpp"
//
//using namespace demos::user;

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

DEFINE_string(hi, "hello", "");

//DEFINE_string(consul_agent_addr, "http://127.0.0.1:8500",
//              "The query string of request consul for discovering service.");
//DEFINE_string(consul_service_discovery_url,
//              "/ui/dc1/services/",
//              "The url of consul for discovering service.");
//DEFINE_string(consul_url_parameter, "?stale&passing",
//              "The query string of request consul for discovering service.");
//DEFINE_int32(consul_connect_timeout_ms, 200,
//             "Timeout for creating connections to consul in milliseconds");
//DEFINE_int32(consul_blocking_query_wait_secs, 60,
//             "Maximum duration for the blocking request in secs.");
//DEFINE_bool(consul_enable_degrade_to_file_naming_service, false,
//            "Use local backup file when consul cannot connect");
//DEFINE_string(consul_file_naming_service_dir, "",
//              "When it degraded to file naming service, the file with name of the "
//              "service name will be searched in this dir to use.");
//DEFINE_int32(consul_retry_interval_ms, 500,
//             "Wait so many milliseconds before retry when error happens");

int main(int argc, char* argv[]) {
    gflags::ParseCommandLineFlags(&argc, &argv, false);
//    LOG(INFO) << "begin to run server ...";
//
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
//    brpc::GetNamingServiceThreadOptions nsOpt;
//
//    brpc::NamingService *ns = new brpc::policy::ConsulNamingService();
//    brpc::NamingServiceThread nst;
//    nst.Start(ns, "thrift", "DemoService", &nsOpt);
//
//    // Wait until Ctrl-C is pressed, then Stop() and Join() the server.
//    server.RunUntilAskedToQuit();
    return 0;
}