namespace java top.szhkai.demos.thrift.user

enum HTTPCode {
    OK = 200,
    SERVER_ERROR = 500,
    NOT_FOUND = 404
}

struct Unit {
    1: optional bool isOk;
    2: optional byte btv;
//    3: i8 i8v;                        // thrift 0.5.0 unsupport
    4: optional i16 i16v;
    5: optional i32 i32v;
    6: optional i64 i64v;
    7: optional double ifv;
    8: optional string strV;
    9: optional binary bitV;
    10: optional HTTPCode ev;
    11: optional list<binary> lv;
    12: optional set<string> setV;
    13: optional map<string, binary> mapV;
}

struct Wrapper {
    1: optional list<Unit> ul;
    2: optional map<string, Unit> um;
}

struct Request {
    1: optional list<Wrapper> ws;
}

struct Response {
    1: optional map<string, Wrapper> ms;
    2: optional list<Wrapper> echo;
    3: optional string jsonEcho;
}

service ComplexIFaceService {
    Response get(1: Request request)
}

