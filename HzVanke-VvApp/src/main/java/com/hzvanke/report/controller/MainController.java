package com.hzvanke.report.controller;


import com.hzvanke.report.pojo.VCSHzMyd;
import com.hzvanke.report.service.face.VCSHzMydService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Main/")
public class MainController extends BaseController {

    // 日志打印
    private static Logger logger = Logger.getLogger(MainController.class.getName());

    @Autowired
    VCSHzMydService mydService;


    @RequestMapping("Default")
    public ModelAndView Main(){
        ModelAndView mv = new ModelAndView("Main/Default");
        try {

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return mv;
    }


    @RequestMapping("popu")
    public ModelAndView popu(VCSHzMyd myd){
        ModelAndView mv = new ModelAndView("Report/Popu");
        try{
            List<Map> maps = mydService.getPupoList(myd.getCreateTime());
            mv.addObject("maps", maps);
            mv.addObject("myd", myd);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return mv;
    }

    @RequestMapping("contact")
    public ModelAndView contact(){
        ModelAndView mv = new ModelAndView("Report/Contact");
        try{

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return mv;
    }

    @RequestMapping("Project")
    public ModelAndView Project(){
        ModelAndView mv = new ModelAndView("Report/Project");
        try{

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return mv;
    }

    @RequestMapping("ProjectPopu")
    public ModelAndView ProjectPopu(String projectId){
        ModelAndView mv = new ModelAndView("Report/ProjectPopu");
        try{
            List<Map> maps = mydService.getPupoList(null);
            mv.addObject("maps", maps);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return mv;
    }

}
