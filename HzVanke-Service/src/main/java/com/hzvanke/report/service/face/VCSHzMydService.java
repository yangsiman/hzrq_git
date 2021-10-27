package com.hzvanke.report.service.face;

import com.hzvanke.report.pojo.VCSHzMyd;
import com.hzvanke.report.service.common.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface VCSHzMydService extends BaseService<VCSHzMyd> {

    List<VCSHzMyd> findCopyList();

    //获取总体满意度数据
    List<Map> getPupoList(Date date);
}
