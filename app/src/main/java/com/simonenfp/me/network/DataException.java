package com.simonenfp.me.network;

/**
 * Created by simonenfp on 2016/8/16.
 */
public class DataException extends RuntimeException{
    public DataException(int resultCode){
        this(getDataExceptionMessage(resultCode));
    }
    public DataException(String message){
        super(message);
    }
    private static String getDataExceptionMessage(int code){
        String message = "";
        switch (code){
            case 100:
                message = "请求出错";
            default:
                message = "未知错误";
        }
        return message;
    }
}
