package com.jlw.ssp.utils;

public class HttpResult {

    private final static int OK = 200;
    private final static int INTERNAL_SERVER_ERROR = 500;

    private int code;
    private Object data;
    private String msg;

    public HttpResult(){

    }

    public HttpResult(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static HttpResult success(){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(OK);
        httpResult.setMsg("操作成功！");
        return httpResult;
    }

    public static HttpResult success(String msg){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(OK);
        httpResult.setMsg(msg);
        return httpResult;
    }

    public static HttpResult fail(){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(INTERNAL_SERVER_ERROR);
        httpResult.setMsg("操作失败！");
        return httpResult;
    }

    public static HttpResult fail(String msg){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(INTERNAL_SERVER_ERROR);
        httpResult.setMsg(msg);
        return httpResult;
    }

    public HttpResult add(Object value){
        this.setData(value);
        return this;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object value) {
        this.data = value;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
