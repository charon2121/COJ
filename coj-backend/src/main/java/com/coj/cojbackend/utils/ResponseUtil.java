package com.coj.cojbackend.utils;

import com.coj.cojbackend.model.response.ApiResponse;
import com.coj.cojbackend.model.response.BaseResponse;
import com.coj.cojbackend.model.response.ResponseCode;
import com.coj.cojbackend.model.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 工具类，用于构建API响应。
 */
public class ResponseUtil {

    /**
     * 构建一个表示成功的API响应。
     *
     * @param data 响应中的数据部分。
     * @param <T>  数据的类型。
     * @return 包含成功响应的ResponseEntity。
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        ApiResponse<T> response = new ApiResponse<>(ResponseCode.SUCCESS, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 构建一个表示成功的API响应，包含额外的消息。
     *
     * @param message 响应中的消息部分。
     * @param <T>     数据的类型。
     * @return 包含成功响应的ResponseEntity。
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        ApiResponse<T> response = new ApiResponse<>(ResponseCode.SUCCESS, message, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 构建一个表示成功的API响应，包含数据和消息。
     *
     * @param message 响应中的消息部分。
     * @param data    响应中的数据部分。
     * @param <T>     数据的类型。
     * @return 包含成功响应的ResponseEntity。
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(ResponseCode.SUCCESS, message, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 构建一个表示验证错误的API响应。
     *
     * @param bindingResult 包含验证错误的信息。
     * @return 包含验证错误响应的ResponseEntity。
     */
    public static ResponseEntity<ValidationErrorResponse> validationError(BindingResult bindingResult) {
        List<ValidationErrorResponse.FieldError> errors = bindingResult.getFieldErrors().stream()
                .map(fieldError -> new ValidationErrorResponse.FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ValidationErrorResponse response = new ValidationErrorResponse(ResponseCode.PARAM_ERROR,
                "Validation Error", errors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

