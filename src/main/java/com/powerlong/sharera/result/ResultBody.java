package com.powerlong.sharera.result;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 2018/5/23
 * Time: 3:14 PM
 */
public class ResultBody {
    private int code;
    private String message;
    private String messageToUser;
    private Object data;

    public ResultBody(ResponseInfoInterface responseInfoInterface) {
        this.code = responseInfoInterface.getCode();
        this.message = responseInfoInterface.getMessage();
        this.messageToUser = responseInfoInterface.getMessageToUser();
    }

    public ResultBody(ResponseInfoInterface responseInfoInterface, String messageToUser) {
        this.code = responseInfoInterface.getCode();
        this.message = responseInfoInterface.getMessage();
        this.messageToUser = messageToUser;
    }

    public ResultBody(Object data) {
        this.code = ResponseInfoEnum.CODE_SUCCESS.getCode();
        this.message = ResponseInfoEnum.CODE_SUCCESS.getMessage();
        this.messageToUser = ResponseInfoEnum.CODE_SUCCESS.getMessageToUser();
        this.data = data;
    }

    public ResultBody() {
        this.code = ResponseInfoEnum.CODE_SUCCESS.getCode();
        this.message = ResponseInfoEnum.CODE_SUCCESS.getMessage();
        this.messageToUser = ResponseInfoEnum.CODE_SUCCESS.getMessageToUser();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageToUser() {
        return messageToUser;
    }

    public void setMessageToUser(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
