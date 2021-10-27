package com.hzvanke.report.sysenum;

/**
 * 系统角色枚举类
 * 作者：蒋斌，开发时间：2019-01-10，版本号：1.1
 */
public class EnumSysRole {
    /**
     角色编码：
     1：系统管理员
     2：平台总控，可以设计模板
     3：项目采购管理，分项目权限
     4：项目设计管理，分项目权限
     5：项目成本管理，分项目权限
     */
    public enum RoleCode {
        //region 定义枚举类型值
        系统管理员(1),
        平台总控(2),
        项目采购管理(3),
        项目设计管理(4),
        项目成本管理(5),
        项目管理(6);
        //endregion

        private int value;

        /**
         * 构造函数
         *
         * @param value
         */
        private RoleCode(int value) {
            this.value = value;
        }

        /**
         * 从int到enum的转换函数
         *
         * @param value
         * @return
         */
        public static RoleCode valueOf(int value) {
            switch (value) {
                case 1:
                    return 系统管理员;
                case 2:
                    return 平台总控;
                case 3:
                    return 项目采购管理;
                case 4:
                    return 项目设计管理;
                case 5:
                    return 项目成本管理;
                case 6:
                    return 项目管理;
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
}

