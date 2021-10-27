package com.hzvanke.report.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectData extends BasePojo {

    public String id;
    public String projectId;
    public BigDecimal ljdf;
    public Long ljpm;
    public BigDecimal ljqtyYbs;
    public String revisitType;
    public Long bmys;           //不满意数

}
