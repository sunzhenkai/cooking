package pub.wii.cook.java.model;

import lombok.Data;

@Data
public class Node {
    String host;
    int shardId;
    EnumTest em;
    EnumTest emInt;
}
