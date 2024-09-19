package pub.wii.cook.java.exception;

public enum BizCode {
    EMPTY_RESULT(3001),
    USER_NOT_EXISTS(3002);

    int value;

    private BizCode(int v) {
        this.value = v;
    }
}
