/*
  https://github.com/apache/thrift/blob/0.13.0/tutorial/java/src/JavaClient.java
 */
package top.szhkai.demos.thrift;

import com.google.gson.Gson;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import top.szhkai.demos.thrift.user.UserRequest;
import top.szhkai.demos.thrift.user.UserResponse;
import top.szhkai.demos.thrift.user.UserService;

public class Thrift13Client {

    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("localhost", 9000);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);
        UserRequest request = new UserRequest();
        request.setTs(10010);
        request.setUid("Tim");
        UserResponse response = client.get(request);
        System.out.println(new Gson().toJson(response));
    }
}
