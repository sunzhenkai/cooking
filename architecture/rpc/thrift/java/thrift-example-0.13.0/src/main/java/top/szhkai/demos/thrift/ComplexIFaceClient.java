/*
  https://github.com/apache/thrift/blob/0.13.0/tutorial/java/src/JavaClient.java
 */
package top.szhkai.demos.thrift;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import top.szhkai.demos.thrift.user.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class ComplexIFaceClient {
    static final Gson gson = new Gson();
    static int times = 1;

    static String buildString() {
        return RandomStringUtils.randomAlphanumeric(1<<10);
    }

    static byte[] buildByteArray() {
        return buildString().getBytes();
    }

    static ByteBuffer buildBinary() {
        return ByteBuffer.wrap(buildByteArray());
    }

    static Unit buildUnit() {
        long ts = System.currentTimeMillis();
        Unit unit = new Unit();
        unit.setIsOk(ts % 2 == 0);
        unit.setBtv((byte) (ts % (2<<7)));
        unit.setI16v((short) (ts % (2<<15)));
        unit.setI32v((int) (ts % (2<<30)));
        unit.setI64v(ts);
        unit.setIfv((double) ts);
        unit.setStrV(UUID.randomUUID().toString());
        unit.setEv(HTTPCode.NOT_FOUND);
        unit.setLv(new ArrayList<>());
        for (int i = 0; i < times; ++i) {
            unit.getLv().add(buildBinary());
        }
        unit.setSetV(new HashSet<>());
        for (int i = 0; i < times; ++i) {
            unit.addToSetV(buildString());
        }
        unit.setMapV(new HashMap<>());
        for (int i = 0; i < times; ++i) {
            unit.putToMapV(buildString(), buildBinary());
        }
        return unit;
    }

    static Wrapper buildWrapper() {
        Wrapper wrapper = new Wrapper();
        wrapper.setUl(new ArrayList<>());
        wrapper.setUm(new HashMap<>());
        for (int i = 0; i < times; ++i) {
            wrapper.addToUl(buildUnit());
            wrapper.putToUm(UUID.randomUUID().toString(), buildUnit());
        }
        return wrapper;
    }

    static Request buildRequest() {
        Request request = new Request();
        request.setWs(new ArrayList<>());
        for (int i = 0; i < times; ++i) {
            request.addToWs(buildWrapper());
        }
        return request;
    }

    public static void testGet(ComplexIFaceService.Client client) throws TException {
        Request request = buildRequest();
        // Request request = new Request();
        TSerializer serializer = new TSerializer(new TCompactProtocol.Factory());
        byte[] data = serializer.serialize(request);
        System.out.println(data.length);
        Response response = client.get(request);

        Request echoReq = new Request().setWs(response.getEcho());
        System.out.println(serializer.serialize(echoReq).length == data.length);
        System.out.println(gson.toJson(request).equals(response.getJsonEcho()));
        System.out.println(gson.toJson(request));
        System.out.println(response.getJsonEcho());
    }

    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("localhost", 9000);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        ComplexIFaceService.Client client = new ComplexIFaceService.Client(protocol);
        testGet(client);
    }
}
