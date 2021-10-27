package com.hzvanke.report.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VCSHzMyd extends BasePojo {

    public String periodYear;       //年
    public String periodMonth;      //月份
    public String areaId;           //AREA_ID
    public String areaName;         //区域名称
    public String companyId;        //COMPANY_ID
    public String companyName;      //公司
    public String revisitType;      //触点
    public String evalIndex;        //指标
    public BigDecimal df;           //当月得分
    public BigDecimal qtyZhs;       //当月总户数
    public BigDecimal qtyYbs;       //当月样本数
    public BigDecimal qyYbs;        //当月区域样本数
    public BigDecimal jtYbs;        //当月集团样本数
    public BigDecimal qyZhs;        //当月区域总户数
    public BigDecimal jtZhs;        //当月集团总户数
    public BigDecimal qyYbsWaa;     //当月区域加权户数
    public BigDecimal jtYbsWaa;     //当月集团户数
    public BigDecimal qydf;         //当月区域得分
    public BigDecimal jtdf;         //当月集团得分
    public BigDecimal qyYbsZb;      //区域内样本占比
    public BigDecimal jtYbsZb;      //集团内样本占比
    public BigDecimal qtyYbsWaa;    //当月加权满意数
    public Long qyjtPm;             //区域在集团的排名
    public Long qyPm;               //区域内得分排名
    public Long jtPm;               //集团内得分排名
    public BigDecimal ljdf;         //累计得分
    public BigDecimal ljqtyZhs;     //累计户数
    public BigDecimal ljqtyYbs;     //累计样本数
    public BigDecimal ljqyYbs;      //累计区域样本数
    public BigDecimal ljjtYbs;      //累计集团样本数
    public BigDecimal ljjtZhs;      //累计集团户数
    public BigDecimal ljqyYbsWaa;   //累计区域加权户数
    public BigDecimal ljjtYbsWaa;   //累计集团加权户数
    public BigDecimal ljqydf;       //累计区域得分
    public BigDecimal ljjtdf;       //累计集团得分
    public BigDecimal ljqyYbsZb;    //累计区域内样本数占比
    public BigDecimal ljjtYbsZb;    //累计集团内样本数占比
    public BigDecimal ljqtyYbsWaa;  //累计加权满意数
    public Long ljqyjtPm;           //累计区域在集团的排名
    public BigDecimal ljqyPm;       //累计区域内排名
    public BigDecimal ljjtPm;       //累计集团排名
    public Long gssl;
    public String qxqy;
    public String qxgs;


}
