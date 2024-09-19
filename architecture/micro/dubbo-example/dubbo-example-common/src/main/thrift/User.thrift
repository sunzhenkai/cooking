namespace java top.szhkai.demos.dubbo
namespace cpp demos.user

struct User {
    1: optional string id;
    2: optional string name;
}

struct UserGetRequest {
    1: optional i64 reqTs;
    2: optional string uid;
}

struct UserGetResponse {
    1: optional bool isOk;
    2: optional User user;
}

struct UserPutRequest {
    1: optional i64 reqTs;
    2: optional User user;
}

struct UserPutResponse {
    1: optional bool isOk;
}

service UerService {
    UserPutResponse put(UserPutRequest request);
    UserGetResponse get(UserGetRequest request);
}