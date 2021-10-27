package com.hzvanke.report.commons;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通用方法调用类
 * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
 *         最后编辑：，编辑时间：，版本号：
 *
 */
public class Utils {
    /**
     * 创建一个新的GUID
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return guid组成的主键ID
     */
    public static String CreateNewKeyid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * MD5加密字符串
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5(String str) {
        MessageDigest md;
        StringBuffer sb = new StringBuffer();
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] data = md.digest();
            int index;
            for (byte b : data) {
                index = b;
                if (index < 0) index += 256;
                if (index < 16) sb.append("0");
                sb.append(Integer.toHexString(index));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获取现在时间
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     * @throws ParseException
     */
    public static Date getNowDate() throws ParseException {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        //ParsePosition pos = new ParsePosition(8);
        //Date currentTime_2 = formatter.parse(dateString,pos);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime_2 = sdf.parse(dateString);
        return currentTime_2;
    }

    /**
     * 得到日期的年月日格式
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param date 日期
     * @return 返回yyyy年MM月dd日格式的日期
     */
    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        return formatter.format(date);
    }

    /**
     * 得到日期的yyyy-MM-dd格式
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式的日期
     */
    public static String getMinDateString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 得到日期的yyyy-MM-dd HH:mm:ss格式
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param date 日期
     * @return yyyy-MM-dd HH:mm:ss格式的日期
     */
    public static String getMaxDateString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * 字符串yyyy-MM-dd格式转成Date类型
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param date 日期字符串
     * @return yyyy-MM-dd格式的日期
     * @throws ParseException
     */
    public static Date getDateByString(String date) throws ParseException {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(date);
    }

    /**
     * 字符串yyyy-MM-dd HH:mm:ss格式转成Date类型
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param date yyyy-MM-dd HH:mm:ss日期字符串
     * @return yyyy-MM-dd HH:mm:ss格式的日期
     * @throws ParseException
     */
    public static Date getDateMaxByString(String date) throws ParseException {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(date);
    }


    /**
     * 将日期转换为yyyy-MM-dd格式的日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDateByDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(formatter.format(date));
    }


    /**
     * 根据开始日期和结束日期，获取包包含参数在内的连续日期集合
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 日期集合
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    /**
     * URL编码
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param str 需要编码的url
     * @return
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL解码
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param str 需要解码的url
     * @return
     */
    public static String URLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Controller中直接弹出提示框，并转向
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param response
     * @param msg      消息信息
     * @param url      转向的url
     * @return
     */
    public static String alertAndForword(HttpServletResponse response, String msg, String url) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print("<script>alert('" + msg + "');window.location.href='"
                    + url + "';</script>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
        return "/Main/UserMainPage";
    }

    /**
     * Controller中直接弹出提示框
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param response
     * @param msg      消息内容
     * @return
     */
    public static void alert(HttpServletResponse response, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print("<script>alert('" + msg + "');</script>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 判断list是否为空
     * @param list
     * @return
     */
    public static boolean  listNotEmpty(List list){
        boolean result=false;
        if(list!=null && list.size()>0){
            result=true;
        }
        return result;
    }

    public static final String DELETE_DELIMTER=",";

    /**
     * 获得所有的id的list
     * @param ids
     * @return
     */
    public static ArrayList<String> getIdList(String ids){
        StringTokenizer stringTokenizer=new StringTokenizer(ids,DELETE_DELIMTER);
        ArrayList<String> idList=new ArrayList<>(stringTokenizer.countTokens());
        while(stringTokenizer.hasMoreTokens()){
            idList.add(stringTokenizer.nextToken());
        }
        return idList;
    }

}
