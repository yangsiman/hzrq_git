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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SysTwoLevelMenu/")
public class SysTwoLevelMenuController {

    // 日志打印
    private static Logger logger = Logger.getLogger(SysTwoLevelMenuController.class.getName());
    @Autowired
    private SysOneLevelMenuService sysOneLevelMenuService;
    @Autowired
    private SysTwoLevelMenuSevice sysTwoLevelMenuSevice;

    //region 新增二级菜单入口
    @RequestMapping("AddMenu")
    public ModelAndView pageAddMenu() {
        ModelAndView view = new ModelAndView("SysConfig/SysTwoLevelMenuAdd");
        //获取一级菜单供选择
        List<SysOnelevelmenu> listSysOneLevelmenu = sysOneLevelMenuService.findAll();

        view.addObject("listSysOneLevelmenu", listSysOneLevelmenu);
        return view;
    }
    //endregion

    //region 新增二级菜单保存操作
    @RequestMapping("SaveAddMenu")
    public void saveAddMenu(SysTwolevelmenu sysTwolevelmenu, HttpServletResponse response) {
        try {
            sysTwolevelmenu.setId(Utils.CreateNewKeyid());
            //验证编号操作
            if (sysTwoLevelMenuSevice.countByCode(sysTwolevelmenu.getMenuCode(), null) > 0) {
                Utils.alertAndForword(response, "菜单编号已存在，请更换。", "/SysTwoLevelMenu/AddMenu");
            } else {
                if (sysTwoLevelMenuSevice.add(sysTwolevelmenu) > 0) {
                    Utils.alertAndForword(response, "数据已保存，操作成功。", "/SysOneLevelMenu/ManageList?menuid=" + sysTwolevelmenu.getOneLevelMenuid());
                } else {
                    Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysTwoLevelMenu/AddMenu");
                }
            }
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysTwoLevelMenu/AddMenu");
        }
    }
    //endregion

    //region 编辑二级菜单操作入口

    /**
     * 编辑二级菜单操作入口
     *
     * @param menuid
     * @return
     */
    @RequestMapping("EditMenu/{menuid}")
    public ModelAndView pageEditMenu(@PathVariable("menuid") String menuid) {
        ModelAndView view = new ModelAndView("SysConfig/SysTwoLevelMenuEdit");
        //获取一级菜单供选择
        List<SysOnelevelmenu> listSysOneLevelmenu = sysOneLevelMenuService.findAll();
        //获取二级菜单数据
        SysTwolevelmenu sysTwolevelmenu = sysTwoLevelMenuSevice.findById(menuid);

        view.addObject("listSysOneLevelmenu", listSysOneLevelmenu);
        view.addObject("sysTwolevelmenu", sysTwolevelmenu);
        return view;
    }
    //endregion

    //region 编辑二级菜单保存操作

    /**
     * 编辑二级菜单保存操作
     *
     * @param sysTwolevelmenu
     * @param response
     */
    @RequestMapping("SaveEditMenu")
    public void saveEditMenu(SysTwolevelmenu sysTwolevelmenu, HttpServletResponse response) {
        try {
            if (sysTwoLevelMenuSevice.countByCode(sysTwolevelmenu.getMenuCode(), sysTwolevelmenu.getId()) > 0) {
                Utils.alertAndForword(response, "菜单编号已存在，请更换。", "/SysTwoLevelMenu/EditMenu/" + sysTwolevelmenu.getId());
            } else {
                sysTwoLevelMenuSevice.update(sysTwolevelmenu);
                Utils.alertAndForword(response, "数据已保存，操作成功。", "/SysOneLevelMenu/ManageList?menuid=" + sysTwolevelmenu.getOneLevelMenuid());
            }
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysTwoLevelMenu/EditMenu/" + sysTwolevelmenu.getId());
        }
    }
    //endregion

    //region 删除二级菜单操作

    /**
     * 删除二级菜单操作
     *
     * @param request
     * @return
     */
    @RequestMapping("DelMenu")
    @ResponseBody
    public Result saveDelMenu(HttpServletRequest request) {
        Result result = new Result();
        try {
            List<String> listMenuId = Arrays.asList(request.getParameterValues("menuId"));
            if (sysTwoLevelMenuSevice.del(listMenuId) > 0) {
                result.setCode(1);
                result.setMsg("指定的二级菜单已被删除。");
            } else {
                result.setCode(2);
                result.setMsg("删除数据时发生错误，操作失败。");
            }
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            result.setCode(2);
            result.setMsg("删除数据时发生错误，操作失败。");
        }
        return result;
    }
    //endregion

    //region 获取最大排序号

    /**
     * 获取最大排序号
     *
     * @param request
     * @return
     */
    @RequestMapping("GetMaxSort")
    @ResponseBody
    public Result getMaxSort(HttpServletRequest request) {
        Result result = new Result();
        try {
            String strOneLevelMenuId = request.getParameter("onelevelmenuid");
            int maxSort = sysTwoLevelMenuSevice.findMaxSort(strOneLevelMenuId) + 1;
            result.setCode(1);
            result.setMsg(String.valueOf(maxSort));
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            result.setCode(2);
            result.setMsg("获取数据时发生错误，操作失败。");
        }
        return result;
    }
    //endregion

    //region 批量修改排序号保存操作
    @RequestMapping("SaveMenuSort")
    @ResponseBody
    public Result SaveMenuSort(HttpServletRequest request) {
        Result result = new Result();
        try {
            String[] arrayMenuIds = request.getParameterValues("menuSortId");
            String[] arrayMenuSorts = request.getParameterValues("menuSort");
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < arrayMenuIds.length; i++) {
                map.put(arrayMenuIds[i], Integer.parseInt(arrayMenuSorts[i]));
            }
            if (sysTwoLevelMenuSevice.updateSort(map)) {
                result.setCode(1);
                result.setMsg("数据已保存，操作成功。");
            } else {
                result.setCode(2);
                result.setMsg("保存数据时发生错误，操作失败。");
            }
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            result.setCode(3);
            result.setMsg("处理数据时发生错误，操作失败。");
        }
        return result;
    }
    //endregion
}
