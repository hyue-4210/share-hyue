package com.powerlong.sharera.result;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 2018/5/23
 * Time: 3:23 PM
 */
public class ResponseInfoException extends Exception {
    private ResponseInfoInterface responseInfoInterface;

    public ResponseInfoException(ResponseInfoInterface responseInfoInterface) {
        this.responseInfoInterface = responseInfoInterface;
    }

    public ResponseInfoInterface getResponseInfoInterface() {
        return responseInfoInterface;
    }

    public void setResponseInfoInterface(ResponseInfoInterface responseInfoInterface) {
        this.responseInfoInterface = responseInfoInterface;
    }
}
