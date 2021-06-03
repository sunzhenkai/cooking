package pub.wii.cook.java.discovery;

import lombok.Data;

import java.util.UUID;

@Data
public class InstanceDetail<T> {
    private String name;
    private String id;
    private int port;
    private String address;
    private String description;
    private T payload;

    public InstanceDetail() {
        this("");
    }

    public InstanceDetail(String description) {
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }
}
