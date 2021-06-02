package pub.wii.cook.java.discovery;

import lombok.Data;

@Data
public class InstanceDetail {
    private String description;

    public InstanceDetail() {
        this("");
    }

    public InstanceDetail(String description) {
        this.description = description;
    }
}
