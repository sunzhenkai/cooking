package top.szhkai.demos.thrift;

import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import top.szhkai.demos.thrift.impl.ComplexIFaceServiceImpl;
import top.szhkai.demos.thrift.user.ComplexIFaceService;

public class Thrift5Server {
    public static void main(String[] args) throws TTransportException {
        // UserServiceImpl impl = new UserServiceImpl();
        ComplexIFaceServiceImpl impl = new ComplexIFaceServiceImpl();
        // UserService.Processor processor = new UserService.Processor(impl);
        ComplexIFaceService.Processor processor = new ComplexIFaceService.Processor(impl);
        TServerSocket transport = new TServerSocket(9000);
        TSimpleServer server = new TSimpleServer(processor, transport);
        System.out.println("Thrift server start to serving ...");

        server.serve();
    }
}
