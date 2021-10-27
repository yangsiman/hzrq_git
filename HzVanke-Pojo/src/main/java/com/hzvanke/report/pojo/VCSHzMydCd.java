package com.hzvanke.report.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VCSHzMydCd extends BasePojo {

    public String periodYear;       //统计年份
    public String periodMonth;      //统计月份
    public String areaId;           //区域ID
    public String areaName;         //区域
    public String companyId;        //公司ID
    public String companyName;      //公司
    public String cityId;           //城市ID
    public String cityName;         //城市
    public String projectId;        //项目ID
    public String projectName;      //项目
    public String stageId;          //分期ID
    public String stageName;        //分期
    public String revisitType;      //调研触点
    public String evalIndex;        //指标
    public BigDecimal qtyZhs;       //户数
    public BigDecimal qtyYbs;       //分期样本数
    public BigDecimal qtyMys;       //分期满意数


}
