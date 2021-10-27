package com.hzvanke.report.pojo;

import lombok.Data;

@Data
public class SysRole extends BasePojo{

    private static final long serialVersionUID = -7864841487632702863L;
    private String roleCode;// * 角色编码：RoleCode

    private String roleName;//角色名称： RoleName

    private String description;//备注说明： Description

}