package com.hzvanke.report.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import com.hzvanke.report.commons.PageUtil;
import java.util.Date;

/**
 * @描述: 基础类，所有实体类都必须继承
 * @作者: 张召
 * @创建时间: 2019-08-14 11:47
 * @版本: 1.0
 **/
@Data
public class BasePojo implements Serializable {

    private static final long serialVersionUID = 7363020110503381839L;

    private String id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间

    @JsonIgnore
    private SysUser createUser;//创建人
    @JsonIgnore
    private String createUserCode;//创建人账号
    @JsonIgnore
    private String createUserName;//创建人姓名

    @JsonIgnore
    private Integer sort;//'排序号'

    private String areaCode;//区域编码

    @JsonIgnore
    private PageUtil pageUtil;

    public void setCreateUser(SysUser sysUser) {
        this.createUser = sysUser;
        this.setCreateUserCode(sysUser.getUserCode());
        this.setCreateUserName(sysUser.getUserName());
    }
}
