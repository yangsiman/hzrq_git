package com.hzvanke.report.controller;

import com.hzvanke.report.commons.Result;
import com.hzvanke.report.commons.Utils;
import com.hzvanke.report.pojo.SysOnelevelmenu;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import com.hzvanke.report.service.face.SysOneLevelMenuService;
import com.hzvanke.report.service.face.SysTwoLevelMenuSevice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("SysOneLevelMenu")
public class SysOneLevelMenuController {
    // 日志打印
    private static Logger logger = Logger.getLogger(SysOneLevelMenuController.class.getName());

    @Autowired
    private SysOneLevelMenuService sysOneLevelMenuService;
    @Autowired
    private SysTwoLevelMenuSevice sysTwoLevelMenuSevice;

    //region 系统一级菜单管理入口
    @RequestMapping("ManageList")
    public ModelAndView pageManageList(HttpServletRequest request){
        ModelAndView view = new ModelAndView("SysConfig/SysMenuList");
        try {
            //获取所有一级菜单数据
            List<SysOnelevelmenu> listSysOneLevelMenu = sysOneLevelMenuService.findAll();

            //获取当前选中的一级菜单ID，如果为空默认为第一个
            String strFocusMenuId = request.getParameter("menuid");
            if (StringUtils.isEmpty(strFocusMenuId)) {
                if (listSysOneLevelMenu.size() > 0) {
                    strFocusMenuId = listSysOneLevelMenu.get(0).getId();
                }
            }
            //获取一级菜单对应的二级菜单信息
            List<SysTwolevelmenu> listSysTwolevelmenu = new ArrayList<>();
            if(!StringUtils.isEmpty(strFocusMenuId)) {
                listSysTwolevelmenu = sysTwoLevelMenuSevice.findByOneLevelMenuId(strFocusMenuId);
            }

            //设置页面数据
            view.addObject("pageFocusMenuId",strFocusMenuId);//当前选中的一级菜单ID
            view.addObject("listSysOneLevelMenu", listSysOneLevelMenu);//所有的一级菜单数据
            view.addObject("listSysTwolevelmenu",listSysTwolevelmenu);//一级菜单对应的二级菜单数据
        } catch (Exception ex) {
            logger.error("处理数据异常",ex);

        }
        return view;
    }
    //endregion

    //region 新增一级菜单入口
    /**
     * 新增一级菜单入口
     * @return
     */
    @RequestMapping("AddMenu")
    public ModelAndView pageAddMenu(){
        ModelAndView view = new ModelAndView("SysConfig/SysOneLevelMenuAdd");
        //获取最大排序号
        int maxSort=sysOneLevelMenuService.findMaxSort() +1;
        view.addObject("pageMaxSort",maxSort);
        return view;
    }
    //endregion

    //region 新增一级菜单保存操作
    @RequestMapping(value = "SaveAddMenu",method = RequestMethod.POST)
    public void saveAddMenu(SysOnelevelmenu sysOnelevelmenu, HttpServletResponse response) throws IOException {
        try {
            sysOnelevelmenu.setId(Utils.CreateNewKeyid());
            sysOnelevelmenu.setStatus("2");
            if (sysOneLevelMenuService.add(sysOnelevelmenu) > 0) {
                Utils.alertAndForword(response, "数据已保存，操作成功。", "/SysOneLevelMenu/ManageList?menuid=" + sysOnelevelmenu.getId());
            } else {
                Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysOneLevelMenu/AddMenu");
            }
        } catch (Exception ex) {
            logger.error("处理数据异常",ex);

            Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysOneLevelMenu/AddMenu");
        }
    }
    //endregion

    //region 编辑一级菜单入口
    /**
     * 编辑一级菜单入口
     * @param menuid 主键ID参数
     * @return
     */
    @RequestMapping("EditMenu/{menuid}")
    public ModelAndView pageEditMenu(@PathVariable("menuid") String menuid){
        ModelAndView view = new ModelAndView("SysConfig/SysOneLevelMenuEdit");
        SysOnelevelmenu sysOnelevelmenu = sysOneLevelMenuService.findById(menuid);
        view.addObject("sysOnelevelmenu",sysOnelevelmenu);
        return view;
    }
    //endregion

    //region 编辑一级菜单保存操作
    /**
     * 编辑一级菜单保存操作
     * @param sysOnelevelmenu
     * @param response
     */
    @RequestMapping("SaveEditMenu")
    public void saveEditMenu(SysOnelevelmenu sysOnelevelmenu, HttpServletResponse response){
        try {
            if (sysOneLevelMenuService.update(sysOnelevelmenu) > 0) {
                Utils.alertAndForword(response, "数据已保存，操作成功。", "/SysOneLevelMenu/ManageList?menuid=" + sysOnelevelmenu.getId());
            } else {
                Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysOneLevelMenu/EditMenu/" + sysOnelevelmenu.getId());
            }
        } catch (Exception ex) {
            logger.error("处理数据异常",ex);

            Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysOneLevelMenu/EditMenu/" + sysOnelevelmenu.getId());
        }
    }
    //endregion

    //region 删除一级菜单操作
    /**
     * 删除一级菜单操作
     * @param menuid 主键ID参数
     * @return
     */
    @RequestMapping("DelMenu/{menuid}")
    @ResponseBody
    public Result saveDelMenu(@PathVariable("menuid")String menuid){
        Result result = new Result();
        try{
            if(sysOneLevelMenuService.del(menuid) > 0){
                result.setCode(1);
                result.setMsg("指定的一级菜单已被删除。");
            }
            else{
                result.setCode(2);
                result.setMsg("删除数据时发生错误，操作失败。");
            }
        }
        catch (Exception ex){
            logger.error("处理数据异常",ex);

            result.setCode(2);
            result.setMsg("删除数据时发生错误，操作失败。");
        }
        return result;
    }
    //endregion
}
