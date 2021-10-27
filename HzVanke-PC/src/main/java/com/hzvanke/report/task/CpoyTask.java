package com.hzvanke.report.task;

import com.hzvanke.report.commons.Utils;
import com.hzvanke.report.pojo.*;
import com.hzvanke.report.service.face.CompanyService;
import com.hzvanke.report.service.face.ProjectService;
import com.hzvanke.report.service.face.VCSHzMydCdService;
import com.hzvanke.report.service.face.VCSHzMydService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class CpoyTask {

    @Autowired
    VCSHzMydService vcsHzMydService;
    @Autowired
    VCSHzMydCdService cdService;
    @Autowired
    ProjectService projectService;
    @Autowired
    CompanyService companyService;


    @Scheduled(cron = "10 0 0 * * ?")
    @Transactional
    public void copy(){
        List<VCSHzMyd> vcsHzMydList = vcsHzMydService.findCopyList();
        List<VCSHzMydCd> cdList = cdService.findCopyList();
        for(VCSHzMydCd cd : cdList){
            Project project = new Project();
            project.setId(Utils.CreateNewKeyid());
            project.setProjectId(cd.getProjectId());
            project.setProjectName(cd.projectName);
            project.setCreateTime(new Date());
            projectService.add(project);
            ProjectData projectData = new ProjectData();
            projectData.setId(Utils.CreateNewKeyid());
//            projectData.setBmys();
        }
        for(VCSHzMyd vcsHzMyd : vcsHzMydList){
            vcsHzMyd.setId(Utils.CreateNewKeyid());
            vcsHzMyd.setCreateTime(new Date());
            vcsHzMydService.add(vcsHzMyd);
            Company company = new Company();
            company.setCompanyId(vcsHzMyd.getCompanyId());
            company.setCompanyName(vcsHzMyd.getCompanyName());
            company.setDf(vcsHzMyd.getDf());
            company.setId(Utils.CreateNewKeyid());
            company.setJtdf(vcsHzMyd.getJtdf());
            company.setJtPm(vcsHzMyd.getJtPm());
            company.setQtyZhs(vcsHzMyd.getQtyZhs());
            company.setRevisitType(vcsHzMyd.getRevisitType());
            company.setLjdf(vcsHzMyd.getLjdf());
            company.setLjqtyYbs(vcsHzMyd.getLjqtyYbs());
            company.setCreateTime(new Date());
            companyService.add(company);
        }


    }

}
