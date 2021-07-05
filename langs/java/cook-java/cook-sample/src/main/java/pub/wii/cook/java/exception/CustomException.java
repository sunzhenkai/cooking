package pub.wii.cook.java.exception;

public class CustomException extends RuntimeException {
    private BizCode code;

    public CustomException(BizCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CustomException(BizCode code, String message) {
        super(message);
        this.code = code;
    }

    public BizCode getCode() {
        return code;
    }
}
