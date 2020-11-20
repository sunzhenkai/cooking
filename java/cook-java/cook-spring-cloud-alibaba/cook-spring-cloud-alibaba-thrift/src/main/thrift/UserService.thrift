namespace java pub.wii.cook.user

include "User.thrift"

struct Request {
    1: required string id;
}

struct Response {
    1: required User.User user;
}

service UserService {
   Response getUser(1: Request request);
}