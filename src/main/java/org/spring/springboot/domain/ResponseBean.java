package org.spring.springboot.domain;

/**
 * Created by Administrator on 2018/2/9.
 */
public class ResponseBean {

    public static final int SUCCESS_CODE = 1;
    public static final int FAIL_CODE = 0;
    public static final int TOKEN_ILLEGAL_CODE = 11;//token过期

    private int code;
    private String message;
    private Object content;

    public ResponseBean() {
    }

    public ResponseBean(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
