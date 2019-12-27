package org.dgut.community.resultenum;

public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(1,"成功"),
    USER_IS_EXIST(-1,"用户存在"),
    PASSWORS_MISTAKE(-1,"原密码错误"),
    USER_NOT_EXIST(-1,"用户不存在"),
    USER_PASSWORS_MISTAKE(-1, "用户名或密码错误"),
    ID_NOT_EXIST(-1,"没有该Id"),
    PASSWORD_IS_NULL(-1, "密码不能为空"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}