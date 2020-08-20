package com.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResMsg<T> implements Serializable {

    private int status;
    private String msg;
    private T data;
    private Long total;
    public List<T> datas = new ArrayList();

    public ResMsg() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    private ResMsg(int status) {
        this.status = status;
    }

    private ResMsg(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ResMsg(int status, List<T> data, Long total) {
        this.status = status;
        this.datas = data;
        this.total = total;
    }

    private ResMsg(int status, List<T> data) {
        this.status = status;
        this.datas = data;
    }

    private ResMsg(int status, String msg, T data) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    private ResMsg(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResCode.SUCCESS.getCode();
    }

    public static <T> ResMsg<T> success() {
        return new ResMsg(ResCode.SUCCESS.getCode());
    }

    public static <T> ResMsg<T> successMsg(String msg) {
        return new ResMsg(ResCode.SUCCESS.getCode(), msg);
    }

    public static <T> ResMsg<T> success(T data) {
        return new ResMsg(ResCode.SUCCESS.getCode(), data);
    }

    public static <T> ResMsg<T> successPage(List<T> data, Long total) {
        return new ResMsg(ResCode.SUCCESS.getCode(), data, total);
    }

    public static <T> ResMsg<T> successList(List<T> data) {
        return new ResMsg(ResCode.SUCCESS.getCode(), data);
    }

    public static <T> ResMsg<T> success(String msg, T data) {
        return new ResMsg(ResCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResMsg<T> error() {
        return new ResMsg(ResCode.ERROR.getCode(), ResCode.ERROR.getDesc());
    }

    public static <T> ResMsg<T> errorMsg(String errorMessage) {
        return new ResMsg(ResCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ResMsg<T> errorCodeMsg(int errorCode, String errorMessage) {
        return new ResMsg(errorCode, errorMessage);
    }
}
