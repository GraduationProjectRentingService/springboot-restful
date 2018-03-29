package org.spring.springboot.exception;

//异常code定义
public enum MyExceptionCode {

    PARAM_REQUIRED_EXCEPTION("PARAM_ERROR", 0),//参数错误
    BUNIESS_EXCEPTION("BUSINESS_ERROR", 0),//业务逻辑错误
    SYSTEM_EXCEPTION("SYSTEM_ERROR", -1),//系统错误
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", 0);//未知错误

    private String code;
    private Integer codeNum;

    private MyExceptionCode(String code, Integer codeNum) {
        this.code  = code;
        this.codeNum = codeNum;
    }

    public static Integer getNumByCode(String code) {
       switch(code) {
           case "PARAM_ERROR" : return 0;
           case "BUSINESS_ERROR" : return 0;
           case "SYSTEM_ERROR" : return -1;
           case "UNKNOWN_EXCEPTION" : return 0;
           default : return null;
       }
    }

    @Override
    public String toString() {
        return String.valueOf(this.codeNum);
    }
}
