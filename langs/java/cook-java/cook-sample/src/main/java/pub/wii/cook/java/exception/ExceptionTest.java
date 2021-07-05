package pub.wii.cook.java.exception;

public class ExceptionTest {
    public static void main(String[] args) {
        try {
            throw new CustomException(BizCode.EMPTY_RESULT, "空结果");
        } catch (CustomException e) {
            System.out.println(e.getCode());
            System.out.println(e.getMessage());
        }
    }
}
