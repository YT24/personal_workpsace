package com.example.yangt.enums;

public class CommonEnums {

    public enum ErrorCode{
        SUCCESS("200","success"),//成功
        ERROR("400","error"),
        Unauthrize("401","user  unauthirze"),
        UserNotExist("10001","user not exist !!!"),
        UserPwdError("10002","user password is error"),
        TokenExpired("10003","token is expired");
        private String val;
        private String resourceKey;
        private ErrorCode(String val, String resourceKey) {
            this.val = val;
            this.resourceKey = resourceKey;
        }
        public String getResourceKey() {
            return resourceKey;
        }
        public String getVal() {
            return val;
        }

    }


    public enum RequestFlag{
        Creat("0","创建"),
        Del("0","删除"),
        Disbale("0","删除"),
        Enbale("0","删除"),
        Update("1","更新");

        private String val;
        private String resourceKey;
        private RequestFlag(String val, String resourceKey) {
            this.val = val;
            this.resourceKey = resourceKey;
        }
        public String getResourceKey() {
            return resourceKey;
        }
        public String getVal() {
            return val;
        }

        public static String getNameByVal(String val) {
            for (RequestFlag flag : RequestFlag.values()) {
                if (flag.getVal().equals(val)) {
                    return flag.getResourceKey();
                }
            }
            return null;
        }

    }


    public enum AppApplyStatus {
        VALID("1"), // 有效
        INVALID("0"), // 无效
        REQUESTING("2");// 请求中
        private String val;

        private AppApplyStatus(String val) {
            this.val = val;
        }
        public String getVal() {
            return val;
        }
    }


    public enum Status {
        VALID("1"), // 有效
        INVALID("0");// 无效
        private String val;

        private Status(String val) {
            this.val = val;
        }
        public String getVal() {
            return val;
        }
    }
}
