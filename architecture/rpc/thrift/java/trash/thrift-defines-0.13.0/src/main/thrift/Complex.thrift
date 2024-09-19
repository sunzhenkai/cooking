namespace java top.szhkai.demos.thrift.user

enum HTTPCode {
    OK = 200,
    SERVER_ERROR = 500,
    NOT_FOUND = 404
}

struct Unit {
    1: bool isOk;
    2: byte btv;
//    3: i8 i8v;                        // thrift 0.5.0 unsupport
    4: i16 i16v;
    5: i32 i32v;
    6: i64 i64v;
    7: double ifv;
    8: string strV;
    9: binary bitV;
    10: HTTPCode ev;
    11: list<binary> lv;
    12: set<string> setV;
    13: map<string, binary> mapV;
}

struct Wrapper {
    1: list<Unit> ul;
    2: map<string, Unit> um;
}

struct Request {
    1: list<Wrapper> ws;
}

struct Response {
    1: map<string, Wrapper> ms;
}

service ComplexIFaceService {
    Response get(1: Request request)
}

