/*
 * Copyright (c) 2020. XiaoMi Inc. All Rights Reserved.
 * Authors: Sun Zhenkai <sunzhenkai@xiaomi.com>.
 * Created on 2020/5/13 19:19:47.
 */

package pub.wii.cook.java.model;

public enum Status {
    SUCCESS(true, 200, "success"),
    SERVER_ERROR(false, 500, "server error"),
    BAD_REQUEST(false, 400, "bad request"),
    FORBIDDEN(false, 403, "forbidden"),
    NOT_FOUND(false, 404, "resources not found");

    private final int code;
    private final String description;
    private final boolean success;

    Status(boolean success, int code, String description) {
        this.success = success;
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return code + ":" + description;
    }
}
