package pub.wii.cook.java.socket;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class FileTransferInfo {
    public static final String TYPE_DELETE = "delete";
    public static final String TYPE_WRITE = "write";
    public static final String TYPE_CLOSE = "close";
    public static final String MSG_DONE = "done";
    public static final String MSG_ERROR = "error";

    private String name;
    private String path;
    private String type;
}
