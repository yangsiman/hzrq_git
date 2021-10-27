package com.hzvanke.report.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1206993918498882744L;
    private String id;

    private SysRole role;//用户所属角色：RoleId

    private String userCode;// 用户登陆帐号：UserCode

    private String password;//用户登陆密码：使用MD5加密，如果是万科域用户，密码可以为空：Password

    private String userType;//用户类别：1万科域用户，2本系统用户：UserType

    private String userName;// 用户姓名：UserName

    private String mobile;// 手机号码：Mobile

    private String email;// 电子邮箱：Email

    private String status;//  用户状态：1允许登陆，2禁止登陆：Status

    private String wxOpenid;//微信OPENID，用户绑定微信时需要保存此值：WxOpenID

    private Date createTime;//创建时间

}