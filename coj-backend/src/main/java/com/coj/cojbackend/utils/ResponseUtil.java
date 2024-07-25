package com.coj.cojbackend.utils;

import com.coj.cojbackend.model.response.ApiResponse;
import com.coj.cojbackend.model.response.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 用于封装请求返回结果
 */
public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        ApiResponse<T> response = new ApiResponse<>(ResponseCode.SUCCESS, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        ApiResponse<T> response = new ApiResponse<>(ResponseCode.SUCCESS, message, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(ResponseCode.SUCCESS, message, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
