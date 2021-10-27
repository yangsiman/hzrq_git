package com.hzvanke.report.commons;

public enum ResultEnum {
    UNKNOW_ERROR(-1, "未知错误"),
    ERROR(100, "操作失败"),
    FOREIGN_ERROR(101, "有主键冲突"),
    CODE_ERROR(10, "验证码输入不正确"),
    USER_ERROR(11, "用户未被授予使用本平台"),//登录的时候看用户名是否存在
    LOGIN_ERROR(12, "用户登录密码错误，请确认"),
    USER_HAS_ERROR(13, "用户已存在"),
    LOGIN_ERROR_LOCK(14,"用户被锁定，请联系管理员！"),
    LOGIN_ERROR_NO_USER(15,"用户不存在，前去绑定！"),
    LOGIN_SUCCESS(200,"登陆成功！"),
    OPERATE_SUCCESS(201,"操作成功！"),
    NO_DATA_ERROR(300,"当前装标没有数据！"),
    CANNOT_DELETE_WITH_DATA(16,"有数据不能删除");
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
