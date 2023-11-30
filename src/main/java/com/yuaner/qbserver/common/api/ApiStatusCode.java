package com.yuaner.qbserver.common.api;

public enum  ApiStatusCode implements IStatusCode{
    SUCCESS(200, "success"),
    FAILED(-1, "fail"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "权限不足"),
    VALIDATE_FAILED(404, "参数检验失败");

    private final Integer code;
    private final String message;

    ApiStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
