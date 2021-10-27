package com.hzvanke.report.commons;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
 * 最后编辑：，编辑时间：，版本号：
 *
 * @param <T> 数据实体类
 */
public class PageUtil<T> {

    /**
     * 每页的数据源
     */
    private List<T> list;

    /**
     * 当前页码
     */
    private int iCurPage = 1;
    /**
     * 每页显示的数量
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private int totalNum;
    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 网页下标的翻页
     */
    private String pageUrl;

    /**
     * 每次查询的开始标志(用作mysql的limit)
     */
    private int start = 0;

    public void setPageUrl(HttpServletRequest request) {
        this.pageUrl = this.resolveUrl(request);
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    /**
     * 设置当前页
     *
     * @param curPage
     */
    public void setiCurPage(String curPage) {
        // 有当前页的要求
        if (null != curPage) {
            iCurPage = Integer.parseInt(curPage);
        }
    }

    public int getiCurPage() {
        return iCurPage;
    }

    public void setiCurPage(int iCurPage) {
        this.iCurPage = iCurPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    /**
     * 设置总记录数的同时设置总页数
     *
     * @param totalNum
     */
    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
        this.totalPage = (this.totalNum % this.pageSize == 0) ? this.totalNum / this.pageSize : this.totalNum / this.pageSize + 1;

    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 实例化页面
     *
     * @param iCurPage 当前页
     * @param pageSize 每页显示的数量
     */
    public PageUtil(int iCurPage, int pageSize) {
        super();
        this.iCurPage = iCurPage;
        this.pageSize = pageSize;
        this.start = (iCurPage - 1) * pageSize;
    }

    /**
     * 实例化页面
     *
     * @param pageSize 每页的数量
     * @param request
     * @return
     * @author 作者: 张召
     * 开发日期:
     * 版本: v1.1
     */
    public PageUtil(int pageSize, HttpServletRequest request) {
        super();
        this.pageSize = pageSize;
        this.iCurPage = getCurrentPageNo(request);
        this.start = (this.iCurPage - 1) * pageSize;
    }

    /***
     * 获取当前的页码
     * @param request
     * @return
     */
    public static int getCurrentPageNo(HttpServletRequest request) {
        int result = 1;
        try {
            String paraValue = "";
            Enumeration keys = request.getParameterNames();
            while (keys.hasMoreElements()) {
                String paraName = (String) keys.nextElement();
                paraName = paraName.replaceAll("<", "").replaceAll(">", "");
                paraValue = request.getParameter(paraName);
                if (paraName.toLowerCase().equals("pageno")) {
                    if (!org.apache.commons.lang.StringUtils.isNumeric(paraValue)) {
                        result = 1;
                    } else {
                        try {
                            result = Integer.parseInt(paraValue);
                            if (result == 0) {
                                result = 1;
                            }
                        } catch (Exception ex) {
                            result = 1;
                        }
                    }
                }
            }
        } catch (Exception ex) {

        }
        return result;
    }

    /**
     * 分页的通用（PC端通用）
     *
     * @param request
     * @return
     */
    public String resolveUrl(HttpServletRequest request) {
        String strUrlString = request.getRequestURI().toString();
        String strQueryString = "";

        String paraValue = "";
        Enumeration keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String paraName = (String) keys.nextElement();
            paraName = paraName.replaceAll("<", "").replaceAll(">", "");
            paraValue = request.getParameter(paraName);
            if (!paraName.toLowerCase().equals("pageno")) {
                strQueryString += "&" + paraName + "=" + paraValue;//组合参数
            }

        }

        StringBuffer option = new StringBuffer("");
        for (int i = 1; i <= this.totalPage; i++) {
            if (i == this.iCurPage) {
                option.append("<option selected='selected' value='" + strUrlString + "?pageNo=" + i + strQueryString +
                        "'>第" + i + "页</option>");
            } else {
                option.append("<option  value='" + strUrlString + "?pageNo=" + i + strQueryString +
                        "'>第" + i + "页</option>");
            }
        }
        StringBuffer classPage = new StringBuffer("");
        int start = this.iCurPage - 2 > 0 ? this.iCurPage - 2 : 1;
        int end = start + 4 <= this.totalPage ? start + 4 : this.totalPage;
        for (int i = start; i <= end; i++) {
            if (i == this.iCurPage)
                classPage.append(" <a class='pagenumb_now'>" + this.iCurPage + " </a>");
            else
                classPage.append("<a class='pagenumb' href='" + strUrlString + "?pageNo=" + i + strQueryString + "'>" + i + " </a>");
        }
        if (end != this.totalPage)
            classPage.append("<a class='pagenumb'  href='#'>......</a>\n");
        else
            classPage.append("<a class='pagenumb' title='最后一页了'  href='#'>......</a>\n");
        //统一的翻页代码
        int nLastPage = this.totalPage;
        if (nLastPage == 0) {
            nLastPage = 1;
        }
        String pageUrl = "<div class='pagenav'><span>总记录数：" + this.totalNum +
                "，当前：" + this.iCurPage + "/" + this.totalPage +
                "，每页：" + this.pageSize + "条</span>\n" +
                " <a class='pagenumb' title='第一页'\n" +
                "  href='" + strUrlString + "?pageNo=1" + strQueryString + "'><<<</a>\n" +
                classPage.toString() +
                " <a class='pagenumb' title='最后一页'\n" +
                "  href='" + strUrlString + "?pageNo=" + nLastPage + strQueryString +
                "'>>>></a>&nbsp;&nbsp;\n" +
                "                        <select onchange='javascript:location.href=this.value;'\n" +
                "                                style=\"padding-left: 4px;padding-right: 18px\">\n" +
                option.toString() + " </select>\n </div>";
        return pageUrl;
    }


    /**
     * 分页的通用（移动端使用）此方法需要重写（暂时无调用）
     * 返回的字符串可以直接使用
     */
    public String resolveUrlForMobile(HttpServletRequest request) {
        String url = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString == null)
            queryString = new String();
        else
            queryString = queryString.replaceAll("&pageNo=\\d*", "").replaceAll("pageNo=\\d*", "").replaceAll("&pageSize=\\d*", "").replaceAll("pageSize=\\d*", "");

        if (this.pageSize != 0)
            queryString = queryString.isEmpty() ? "pageSize=" + pageSize : queryString + "&pageSize=" + pageSize;
        url = queryString.isEmpty() ? url : url + "?" + queryString;

        //StringBuffer classPage = new StringBuffer("");
        int start = this.iCurPage - 2 > 0 ? this.iCurPage - 2 : 1;
        int end = start + 4 <= this.totalPage ? start + 4 : this.totalPage;

        String _returnValue = "";
        if (this.totalNum <= 0) {
            _returnValue = "<table border='0' cellpadding='0' cellspacing='0' class='pagination_frame'>";
            _returnValue += "<tr>";
            _returnValue += "<td class='pagination_frame_btnbox1'><a href='#' class='pagination_frame_a1'>首页</a></td>";
            _returnValue += "<td class='pagination_frame_btnbox2'><a href='#' class='pagination_frame_a2'>上一页</a></td>";
            _returnValue += "<td><div class='pagination_frame_div2'>0/0</div></td>";
            _returnValue += "<td class='pagination_frame_btnbox2'><a href='#' class='pagination_frame_a2'>下一页</a></td>";
            _returnValue += "<td class='pagination_frame_btnbox1'><a href='#' class='pagination_frame_a3'>尾页</a></td>";
            _returnValue += "</tr>";
            _returnValue += "</table>";
        } else {
            //提示
            _returnValue = "<table border='0' cellpadding='0' cellspacing='0' class='pagination_frame'>";
            _returnValue += "<tr>";
            //第一页
            _returnValue += "<td class='pagination_frame_btnbox1'><a href='";
            if (this.totalPage > 0 && this.iCurPage > 1)
                _returnValue += url + "&pageNo=1";
            else
                _returnValue += "#";
            _returnValue += "' class='pagination_frame_a1'>首页</a></td>";

            //上一页
            _returnValue += "<td class='pagination_frame_btnbox2'><a href='";
            if (this.totalPage > 0 && this.iCurPage > 1)
                _returnValue += url + "&pageNo=" + String.valueOf(this.iCurPage - 1);
            else
                _returnValue += "#";
            _returnValue += "' class='pagination_frame_a2'>上一页</a></td>";

            //页码和总页数
            _returnValue += "<td><div class='pagination_frame_div2'>" + this.iCurPage + "/" + this.totalPage + "</div></td>";

            //下一页
            _returnValue += "<td class='pagination_frame_btnbox2'><a href='";
            if (this.totalPage > 1 && this.iCurPage < this.totalPage)
                _returnValue += url + "&pageNo=" + String.valueOf(this.iCurPage + 1);
            else
                _returnValue += "#";
            _returnValue += "' class='pagination_frame_a2'>下一页</a></td>";

            //最后页
            _returnValue += "<td class='pagination_frame_btnbox1'><a href='";
            if (this.totalPage > 1 && this.iCurPage < this.totalPage)
                _returnValue += url + "&pageNo=" + this.totalPage;
            else
                _returnValue += "#";
            _returnValue += "' class='pagination_frame_a3'>尾页</a></td>";

            _returnValue += "</tr>";
            _returnValue += "</table>";
        }

        return _returnValue;
    }
}
