package com.coj.cojbackend.model.response;

import lombok.Data;

@Data
public class BaseResponse {
    private int code;
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
