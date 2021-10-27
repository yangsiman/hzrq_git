package com.hzvanke.report.service.impl;

import com.hzvanke.report.dao.VCSHzMydCdDao;
import com.hzvanke.report.pojo.VCSHzMydCd;
import com.hzvanke.report.service.common.BaseServiceImpl;
import com.hzvanke.report.service.face.VCSHzMydCdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VCSHzMydCdServiceImpl extends BaseServiceImpl<VCSHzMydCd> implements VCSHzMydCdService {

    @Autowired
    VCSHzMydCdDao dao;

    @Override
    public List<VCSHzMydCd> findCopyList() {
        return dao.findCopyList();
    }
}
