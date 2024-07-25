package com.coj.cojbackend.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ApiResponse 类用于封装 API 的响应结果。
 * @param <T> 响应数据的泛型类型，允许响应数据为任意类型。
 */
// @EqualsAndHashCode(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiResponse<T> extends BaseResponse {
    /**
     * 响应数据，用于承载 API 调用返回的具体数据。
     */
    private T data;

    public ApiResponse() {
        super(0, null);
    }

    /**
     * 构造函数，用于创建一个包含状态码和消息的 ApiResponse 对象。
     * @param code 响应的状态码。
     * @param message 响应的消息。
     */
    public ApiResponse(int code, String message) {
        super(code, message);
    }

    /**
     * 构造函数，用于创建一个包含状态码和数据的 ApiResponse 对象。
     * @param code 响应的状态码。
     * @param data 响应的数据。
     */
    public ApiResponse(int code, T data) {
        super(code, null);
        this.data = data;
    }

    /**
     * 完全构造函数，用于创建一个包含状态码、消息和数据的 ApiResponse 对象。
     * @param code 响应的状态码。
     * @param message 响应的消息。
     * @param data 响应的数据。
     */
    public ApiResponse(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }
}
