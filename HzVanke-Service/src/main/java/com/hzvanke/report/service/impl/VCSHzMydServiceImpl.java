package com.hzvanke.report.service.impl;

import com.hzvanke.report.dao.VCSHzMydDao;
import com.hzvanke.report.pojo.VCSHzMyd;
import com.hzvanke.report.service.common.BaseServiceImpl;
import com.hzvanke.report.service.face.VCSHzMydService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VCSHzMydServiceImpl extends BaseServiceImpl<VCSHzMyd> implements VCSHzMydService {

    @Autowired
    VCSHzMydDao dao;

    @Override
    public List<VCSHzMyd> findCopyList() {
        return dao.findCopyList();
    }

    @Override
    public List<Map> getPupoList(Date date) {
        return dao.getPupoList(date);
    }
}
