package pub.wii.cook.java.model;

public class Response<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    public Response(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Response(Status status) {
        this(status.isSuccess(), status.getCode(), status.getDescription());
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Response<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public static <T> Response<T> ok() {
        return new Response<>(Status.SUCCESS);
    }

    public static <T> Response<T> ok(T data) {
        return new Response<T>(Status.SUCCESS).setData(data);
    }

    public static <T> Response<T> ok(T data, String msg) {
        return ok(data).setMessage(msg);
    }

    public static <T> Response<T> error(Status status) {
        return new Response<>(status);
    }

    public static <T> Response<T> error(Status status, String msg) {
        return new Response<T>(status).setMessage(msg);
    }
}
