package com.powerlong.sharera.result;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 2018/5/23
 * Time: 3:15 PM
 */
public enum ThirdInfoEnum implements ResponseInfoInterface {
    //1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿
    GXYZ(1, "GXYZ", "云纸"),
    GLMB(2, "GLMB", "乐摩吧"),
    GXLD(3, "GXLD", "来电"),
    XMLW(4, "XMLW", "熊猫溜娃"),
    GXMS(5, "GXMS", "摩伞"),
    GHXL(6, "GHXL", "黄小鹿");



    private int code;
    private String message;
    private String messageToUser;

    ThirdInfoEnum(int code, String message, String messageToUser) {
        this.code = code;
        this.message = message;
        this.messageToUser = messageToUser;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessageToUser() {
        return messageToUser;
    }


}
