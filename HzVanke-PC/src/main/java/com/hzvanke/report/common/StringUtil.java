package com.hzvanke.report.common;

import com.hzvanke.report.commons.DateUtils2;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StringUtil {

    /**
     * 判断字符串是否存在并且为空
     *
     * @param str
     * @return true空false不为空
     */
    public static boolean isEmpty(String str) {
        if (str != null && str.trim().length() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(BigDecimal str) {
        if (str != null) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(BigDecimal str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(List list) {
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    /**
     * 获取字符串的值，用于导出
     *
     * @return java.lang.String
     */
    public static String getValue(String str) {
        if (str != null && str.trim().length() > 0) {
            return str;
        }
        return "";
    }

    /**
     * 判断是否为空,获取字符串的值，用于导出
     *
     * @return java.lang.String
     */
    public static String getValue(Object obj) {
        if (obj != null && !obj.equals("")) {
            return obj.toString();
        }
        return "";
    }

    /**
     * 获取日期的值，用于导出
     *
     * @return java.lang.String
     */
    public static String getValue(Date date) {
        String str = DateUtils2.formatDate(date, "yyyy-MM-dd HH:mm");
        if (isNotEmpty(str)) {
            return str;
        }
        return "";
    }

    public static String getValue(Date date, String pattern) {
        String str = DateUtils2.formatDate(date, pattern);
        if (isNotEmpty(str)) {
            return str;
        }
        return "";
    }

    /**
     * 获取字符串的值，用于导出
     *
     * @return java.lang.String
     */
    public static String getValue(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal + "";
        }
        return "";
    }

    public static String compile(String obj) {
        return obj.replace("&middot;", "·").replace("&hellip;", "…").replace("<br/>", "\r\n")
                .replace("&ldquo;", "\"").replace("&rdquo;", "\"").replace("&mdash;", "—");

    }

    public static String join( final String[] array, final String separator) {
        if (array == null || separator == null) {
            return "";
        }
        final int noOfItems = array.length;
        if (noOfItems <= 0) {
            return "";
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        for (int i = 0; i < noOfItems; i++) {
            if (isNotEmpty(array[i])) {
                if (i > 0) {
                    buf.append(separator);
                }
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 用作导入的时候多用户的数组格式输出
     *
     * @param arr
     * @return java.lang.String[]
     * @author 作者: 张召
     * @date 2019/6/25 下午 3:23
     * 版本: v1.1
     */
    public static String[] getArrayValue(String arr) {
        if (isEmpty(arr)) {
            return null;
        }
        String[] str;
        arr = arr.trim();
        if (arr.contains(",")) {
            str = StringUtils.split(arr, ",");
        } else if (arr.contains("，")) {
            str = StringUtils.split(arr, "，");
        } else if (arr.contains(";")) {
            str = StringUtils.split(arr, ";");
        } else if (arr.contains("；")) {
            str = StringUtils.split(arr, "；");
        } else if (arr.contains("、")) {
            str = StringUtils.split(arr, "、");
        } else {
            str = StringUtils.split(arr, "/");
        }
        return str;
    }

    /**
     * 用于某些关键信息的隐藏（姓名，电话，身份证）
     *
     * @return java.lang.String
     */
    public static String toHideString(String name) {
        if (isEmpty(name)) return "";
        String[] names = name.split(",");
        StringBuffer sb = new StringBuffer();
        for (String str : names) {
            int num = str.length();
            if (num < 6) {
                //姓名
                switch (num) {
                    case 1:
                        sb.append(str);
                        break;
                    case 2:
                        sb.append(str.replaceFirst(str.substring(1), "*"));
                        break;
                    case 3:
                        sb.append(str.replaceFirst(str.substring(1, num - 1), "*"));
                        break;
                    default:
                        sb.append(str.replaceFirst(str.substring(2, num - 1), "*"));
                        break;
                }
            } else {
                //电话号码，身份证
                int index = num <= 11 ? 3 : 6;
                char[] r = str.toCharArray();
                for (int i = index; i < r.length; i++) {
                    if (i < (num - 4)) {
                        r[i] = '*';
                    }
                }
                sb.append(r);
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (isNotEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (isNotEmpty(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * 读取本地文件的xml转换为string 类型
     *
     * @param xmlPath 路径
     * @return
     */
    public static String XmlToString(String xmlPath) {

        try {
            // File f = new File("D:/a.xml");
            StringBuffer buffer = new StringBuffer("");
            InputStream is = new FileInputStream(xmlPath);
            String line; // 用来保存每行读取的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"));
            line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                buffer.append(line); // 将读到的内容添加到 buffer 中
                buffer.append("\n"); // 添加换行符
                line = reader.readLine(); // 读取下一行
            }
            reader.close();
            is.close();
            String _baseInfoXml = URLDecoder.decode(buffer.toString(), "utf-8");
            return _baseInfoXml;

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return "";
    }

    /**
     * 生成随机字符串
     *
     * @param length 用户要求产生字符串的长度
     * @return java.lang.String
     * @author 作者: 张召
     * @date 2020/4/27 12:13
     * 版本: v1.1
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 特殊字符转化
     *
     * @param str
     * @return java.lang.String
     * @author 作者: 张召
     * @date 2020/4/27 12:15
     * 版本: v1.1
     */
    public static String htmlReplace(String str) {
        str = str.replace("&ldquo;", "“");
        str = str.replace("&rdquo;", "”");
        str = str.replace("&nbsp;", " ");
        str = str.replace("&", "&amp;");
        str = str.replace("&#39;", "'");
        str = str.replace("&rsquo;", "’");
        str = str.replace("&mdash;", "—");
        str = str.replace("&ndash;", "–");
        return str;
    }
}
