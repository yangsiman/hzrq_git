package com.hzvanke.report.sysenum;

/**
 * 系统用户枚举类
 * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
 */
public class EnumSysUser {
    //region 用户状态

    /**
     * 用户状态
     * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     */
    public enum Status {
        //region 定义枚举类型值
        正常登陆(1),
        限制登陆(2);
        //endregion

        private int value;

        /**
         * 构造函数
         *
         * @param value
         */
        private Status(int value) {
            this.value = value;
        }

        /**
         * 从int到enum的转换函数
         *
         * @param value
         * @return
         */
        public static Status valueOf(int value) {
            switch (value) {
                case 1:
                    return 正常登陆;
                case 2:
                    return 限制登陆;
                default:
                    return null;
            }
        }

        /**
         * 输出枚举值的数字值
         *
         * @return
         */
        public int value() {
            return this.value;
        }
    }

    /**
     * 用户类别
     */
    public enum Type {
        //region 定义枚举类型值
        对接用户(1),
        系统用户(2);
        //endregion

        private int value;

        /**
         * 构造函数
         *
         * @param value
         */
        private Type(int value) {
            this.value = value;
        }

        /**
         * 从int到enum的转换函数
         *
         * @param value
         * @return
         */
        public static Type valueOf(int value) {
            switch (value) {
                case 1:
                    return 对接用户;
                case 2:
                    return 系统用户;
                default:
                    return null;
            }
        }

        /**
         * 输出枚举值的数字值
         *
         * @return
         */
        public int value() {
            return this.value;
        }
    }

    /**
     * 获取用户状态文字描述
     *
     * @param source
     * @return
     */
    public static String getStatusFont(Object source) {
        String result = "";
        try {
            int value = Integer.parseInt(source.toString());
            result = Status.valueOf(value).name();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //endregion
}
