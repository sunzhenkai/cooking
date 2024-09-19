namespace java top.szhkai.demos.thrift.user

struct User {
    1: string name;
    2: i32 age;
}

struct UserRequest {
    1: i64 ts;
    2: string uid;
}

struct UserResponse {
    1: string msg;
    2: User user;
}

service UserService {
    UserResponse get(1: UserRequest request);
}