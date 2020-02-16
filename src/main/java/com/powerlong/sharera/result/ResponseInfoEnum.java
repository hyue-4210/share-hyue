package com.powerlong.sharera.result;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 2018/5/23
 * Time: 3:15 PM
 */
public enum ResponseInfoEnum implements ResponseInfoInterface {

    CODE_SUCCESS(200, "ok", "成功"),

    // 4xxx，定义 API 请求内容的错误
    CODE_ERR_MISSING_PARAMETERS(4001, "Missing parameters!", "缺少参数!"),
    CODE_ERR_MISSING_HEADER(4002, "Missing header!", "缺少头文件!"),
    TOKEN_INCORRECT_OR_HAS_EXPIRED(40001, "The token is incorrect or has expired!", "token不正确或者已过期!"),


    // 5xxx，定义服务端对身份鉴权产生的错误
    CODE_ERR_INVALID_ACCESS_TOKEN(5001, "Invalid access token!", "无效的 accessToken!"),
    CODE_ERR_INVALID_SIGNATURE(5002, "Invalid signature!", "鉴权失败，无效的 signature!"),
    CODE_ERR_INVALID_CODE(5003, "Invalid code!", "无效的 code!"),
    CODE_ERR_INVALID_SECRETID(5004, "Invalidsecretid!", "密钥非法!"),
    CODE_ERR_SECRETID_NOT_FOUND(5005, "Secretidnotfound!", "密钥不存在!"),
    CODE_ERR_SIGNATURE_FAILURE(5006, "Signature failure!", "签名失败!"),



    // 6xxx，定义服务端处理请求产生的非业务错误
    CODE_ERR_SERVER(6001, "Server error!", "内部错误!"),
    CODE_ERR_CONNECT_TIMEOUT(6002, "Connect timeout!", "连接超时!"),

    // 7xxx，定义服务端业务处理的所有业务错误
    CODE_ERR_PHONE(7001, "Phone number is incorrect!", "手机号码不正确!"),
    CODE_ERR_PHONE_BINDING(7002, "Phone number is already bound!", "手机号码已绑定!"),
    CODE_ERR_NOT_REGISTERED(7003, "User is not registered!", "用户还未注册!"),
    CODE_ERR_PHONE_CODE(7004, "Verification code is incorrect!", "验证码不正确!"),
    CODE_ERR_EMAIL(7005, "Email is incorrect!", "邮箱不正确!"),
    CODE_ERR_RESOURCE_NOT_FOUND(7006, "Resource not found!", "资源未找到!"),
    CODE_ERR_RESOURCE_UPDATE(7007, "Resource update failed!", "资源操作失败!"),
    CODE_ERR_REPEAT_ORDER(7008, "repeat order!", "重复订单!"),
    CODE_ERR_CHENGE_LOCAL(7009, "to local!", "请切换到设备所在商场!");

    private int code;
    private String message;
    private String messageToUser;

    ResponseInfoEnum(int code, String message, String messageToUser) {
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
