package pub.wii.cook.thrift;

import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import pub.wii.cook.User;

public class DeSe {
    public static void main(String[] args) throws TException {
        User user = new User();
        user.setId("00");
        user.setName("wii");
        user.setAge(18);
        System.out.println(user);

        TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());
        byte[] serialized = serializer.serialize(user);
        System.out.println("serialized binary size: " + serialized.length);


        User ou = new User();
        TDeserializer deserializer = new TDeserializer(new TBinaryProtocol.Factory());
        deserializer.deserialize(ou, serialized);
        System.out.println(ou.getName());
    }
}
