package com.coj.cojbackend.model.response;

public class ResponseCode {
    /**
     * 业务处理成功，没有任何错误
     */
    public static final int SUCCESS = 200;

    /**
     * 请求携带的参数没有通过校验
     */
    public static final int PARAM_ERROR = 300;


    public static final int ERROR = 500;
}
