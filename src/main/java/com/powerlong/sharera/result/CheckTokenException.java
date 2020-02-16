package com.powerlong.sharera.result;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 2018/6/13
 * Time: 9:07 PM
 */
public class CheckTokenException extends RuntimeException {
	private static final long serialVersionUID = 8175240895319916234L;
	private ResponseInfoInterface responseInfoInterface;

    public CheckTokenException(ResponseInfoInterface responseInfoInterface) {
        this.responseInfoInterface = responseInfoInterface;
    }

    public ResponseInfoInterface getResponseInfoInterface() {
        return responseInfoInterface;
    }
}
