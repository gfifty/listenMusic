package com.catone.twentyone.namelessmusic.service.util.service.util;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 统一返回
 * @author zhonghui
 * @date 2019/9/20
 */
@Data
public class FwResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;

    private String message;

    boolean success;

    Date time;

    private T data;
    private String token;

    public static <T> FwResult<T> ok() {
        return restResult(null, com.catone.twentyone.namelessmusic.service.util.FwConstants.SUCCESS, true, com.catone.twentyone.namelessmusic.service.util.FwConstants.MSG_SUCCESS);
    }
    public static <T> FwResult<T> okMsg(String msg) {
        return restResult(null, com.catone.twentyone.namelessmusic.service.util.FwConstants.SUCCESS, true,msg);
    }
    public static <T> FwResult<T> ok(T data) {
        return restResult(data, com.catone.twentyone.namelessmusic.service.util.FwConstants.SUCCESS, true, com.catone.twentyone.namelessmusic.service.util.FwConstants.MSG_SUCCESS);
    }

    public static <T> FwResult<T> ok(T data, String msg) {
        return restResult(data, com.catone.twentyone.namelessmusic.service.util.FwConstants.SUCCESS, true,msg);
    }
    public static <T> FwResult<T> okMeta(T data) {
        return restResult(data, com.catone.twentyone.namelessmusic.service.util.FwConstants.SUCCESS, true,null);
    }
    public static <T> FwResult<T> ok(T data, String msg, Object meta) {
        return restResult(data, com.catone.twentyone.namelessmusic.service.util.FwConstants.SUCCESS, true,msg);
    }

    public static <T> FwResult<T> failed() {
        return restResult(null, com.catone.twentyone.namelessmusic.service.util.FwConstants.FAIL,false, com.catone.twentyone.namelessmusic.service.util.FwConstants.MSG_FAIL);
    }

    public static <T> FwResult<T> failedMsg(String msg) {
        return restResult(null, com.catone.twentyone.namelessmusic.service.util.FwConstants.FAIL, false,msg);
    }

    public static <T> FwResult<T> failedMsg(int code,String msg) {
        return restResult(null, code, false,msg);
    }

    public static <T> FwResult<T> tokenFailedMsg(String msg) {
        return restResult(null, com.catone.twentyone.namelessmusic.service.util.FwConstants.RELOGIN, false,msg);
    }

    public static <T> FwResult<T> urlNotFoundMsg(String msg) {
        return restResult(null, com.catone.twentyone.namelessmusic.service.util.FwConstants.NOTFOUND, false,msg);
    }

    public static <T> FwResult<T> failed(T data) {
        return restResult(data, com.catone.twentyone.namelessmusic.service.util.FwConstants.FAIL, false, com.catone.twentyone.namelessmusic.service.util.FwConstants.MSG_FAIL);
    }

    public static <T> FwResult<T> failed(T data, String msg) {
        return restResult(data, com.catone.twentyone.namelessmusic.service.util.FwConstants.FAIL, false,msg);
    }

    private static <T> FwResult<T> restResult(T data, int code, boolean success,String msg) {
        FwResult fwResult = new FwResult();
        fwResult.setStatus(code);
        fwResult.setSuccess(success);
        fwResult.setData(data);
        fwResult.setMessage(msg);
        fwResult.setTime(new Date());
        return fwResult;
    }
    // 设置 token
    public FwResult<T> setToken(String token) {
        this.token = token;
        return this;
    }


}
