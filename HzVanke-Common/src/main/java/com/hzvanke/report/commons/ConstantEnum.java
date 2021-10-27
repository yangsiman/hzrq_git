package com.hzvanke.report.commons;

public enum ConstantEnum {

    FBTYPE_LASTEST(1, "最新版"),
    FBTYPE_PUBLISH(2,"发布版"),
    FYSTATUS_NOT_HAD(1,"未封样"),
    FYSTATUS_HAD(2,"已封样"),
    CONFIG_TYPE_INPUT(1,"手动录入"),
    CONFIG_TYPE_COMPANY(2,"公司装标"),
    CONFIG_TYPE_PROJECT(3,"项目装标"),
    IS_SALE_VERSION(1,"销售版本"),
    NOT_SALE_VERSION(0,"非销售版本"),
    FLOW_NOT_CREATED(1,"流程未创建"),
    FLOW_CREATED_SUCCESS(2,"流程创建成功"),
    FLOW_CREATED_FAIL(0,"流程创建失败"),
    FLOW_NOT_EXIST(3,"不存在该流程实例"),
    FLOW_APPROVING(4,"正在审批流程中"),
    FLOW_APPROVED(5,"已审批通过"),
    FLOW_DENIED(6,"已被拒绝"),
    FLOW_DELETED(7,"已被删除"),
    FLOW_APPROVEDELETED(8,"申请删除"),
    FLOW_DOUBT(9,"有疑义"),
    FLOW_RETURN(10,"退回"),
    MY_PUBLISH(0,"我的发布"),
    PUBLISH_HZ(1,"发布汇总");
    private Integer code;
    private String msg;

    ConstantEnum(Integer code, String msg) {
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
