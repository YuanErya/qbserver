package com.yuaner.qbserver.common.api;

import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

@Data
public class ApiResult <T> implements Serializable {
    private long code;
    private T data;
    private String message;

    /**
     * @param code    业务状态码
     * @param message 描述
     * @param data    结果集
     */
    public ApiResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 根据状态码创建对象
     * @param statusCode 状态码对象
     */
    public ApiResult(IStatusCode statusCode) {
        statusCode = Optional.ofNullable(statusCode).orElse(ApiStatusCode.FAILED);
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    /**
     * 成功且无需返回数据
     * @param
     * @return {code:200,message:操作成功,data:自定义}
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<T>(ApiStatusCode.SUCCESS.getCode(),ApiStatusCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功且无需返回数据,自定义返回消息
     * @param
     * @return {code:200,message:操作成功,data:自定义}
     */
    public static <T> ApiResult<T> success(String message) {
        return new ApiResult<T>(ApiStatusCode.SUCCESS.getCode(),message, null);
    }

    /**
     * 成功且返回数据
     * @param
     * @return {code:200,message:操作成功,data:自定义}
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(ApiStatusCode.SUCCESS.getCode(),ApiStatusCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功且返回数据,自定义返回信息
     * @param
     * @return {code:200,message:操作成功,data:自定义}
     */
    public static <T> ApiResult<T> success(T data,String message) {
        return new ApiResult<T>(ApiStatusCode.SUCCESS.getCode(),message, data);
    }

    /**
     * 失败
     */
    public static <T> ApiResult<T> failed() {
        return new ApiResult<T>(ApiStatusCode.FAILED);
    }

    /**
     * 失败，无返回数据
     * @param statusCode 状态码对象
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> failed(IStatusCode statusCode) {
        return new ApiResult<T>(statusCode.getCode(),statusCode.getMessage(),null);
    }

    /**
     * 失败,自定义失败信息
     */
    public static <T> ApiResult<T> failed(String message) {
        return new ApiResult<T>(ApiStatusCode.FAILED.getCode(),message,null);
    }

    /**
     * 失败状态码，自定义信息
     * @param statusCode
     * @param message
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> failed(IStatusCode statusCode,String message) {
        return new ApiResult<T>(statusCode.getCode(),message,null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ApiResult<T> validateFailed() {
        return failed(ApiStatusCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ApiResult<T> validateFailed(String message) {
        return new ApiResult<T>(ApiStatusCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ApiResult<T> unauthorized(T data) {
        return new ApiResult<T>(ApiStatusCode.UNAUTHORIZED.getCode(), ApiStatusCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ApiResult<T> forbidden(T data) {
        return new ApiResult<T>(ApiStatusCode.FORBIDDEN.getCode(), ApiStatusCode.FORBIDDEN.getMessage(), data);
    }








}
