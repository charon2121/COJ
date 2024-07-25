package com.coj.cojbackend.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 表示验证错误响应的类。
 * 用于在客户端请求验证失败时，返回详细的错误信息。
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationErrorResponse extends BaseResponse {
    /**
     * 验证错误的详细信息列表，包括出错的字段和错误消息。
     */
    private List<FieldError> errors;

    /**
     * 构造函数，初始化验证错误响应。
     *
     * @param code    错误代码
     * @param message 错误消息
     * @param errors  验证错误的详细信息列表
     */
    public ValidationErrorResponse(int code, String message, List<FieldError> errors) {
        super(code, message);
        this.errors = errors;
    }

    /**
     * 表示验证错误中具体字段错误的内部类。
     * 用于详细描述哪个字段发生了验证错误及其错误消息。
     */
    @Data
    public static class FieldError {
        /**
         * 发生验证错误的字段名。
         */
        private String field;
        /**
         * 验证错误的具体消息。
         */
        private String error;

        /**
         * 构造函数，初始化字段错误。
         *
         * @param field  发生验证错误的字段名
         * @param error  验证错误的具体消息
         */
        public FieldError(String field, String error) {
            this.field = field;
            this.error = error;
        }
    }
}