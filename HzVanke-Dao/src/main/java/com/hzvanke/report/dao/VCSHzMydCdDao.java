package com.hzvanke.report.dao;

import com.hzvanke.report.pojo.VCSHzMydCd;

import java.util.List;

public interface VCSHzMydCdDao extends CareDao<VCSHzMydCd> {

    List<VCSHzMydCd> findCopyList();
}
