package com.hzvanke.report.dao;

import com.hzvanke.report.pojo.VCSHzMyd;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface VCSHzMydDao extends CareDao<VCSHzMyd> {

    List<VCSHzMyd> findCopyList();

    List<Map> getPupoList(Date date);
}
