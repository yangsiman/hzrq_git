package com.hzvanke.report.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Company extends BasePojo {

    public String id;
    public String companyName;
    public String companyId;
    public BigDecimal df;           //公司触点得分
    public Long jtPm;         //集团排名
    public BigDecimal qtyZhs;       //户数
    public String revisitType;      //触点
    public BigDecimal jtdf;         //集团触点得分
    public BigDecimal ljqtyYbs;     //累计样本数
    public BigDecimal ljdf;         //公司累计得分

}
