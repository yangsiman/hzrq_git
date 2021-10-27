package com.hzvanke.report.service.face;

import com.hzvanke.report.pojo.VCSHzMydCd;
import com.hzvanke.report.service.common.BaseService;

import java.util.List;

public interface VCSHzMydCdService extends BaseService<VCSHzMydCd> {

    List<VCSHzMydCd> findCopyList();
}
