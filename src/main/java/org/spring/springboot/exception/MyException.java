package org.spring.springboot.exception;

/**
 * Created by chao.lin on 17/5/1.
 */
public class MyException extends RuntimeException{

    private MyExceptionType errorType;
    private MyExceptionCode errorCode;
    private Object errorData;

    public MyException (MyExceptionCode errorCode, MyExceptionType errorType, String message) {
        super(message);
        this.errorType = errorType;
        this.errorCode = errorCode;
    }

    public MyException (MyExceptionCode errorCode, MyExceptionType errorType, String message, Object errorData) {
        super(message);
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.errorData = errorData;
    }

    public MyExceptionType getErrorType() {
        return errorType;
    }

    public void setErrorType(MyExceptionType errorType) {
        this.errorType = errorType;
    }

    public MyExceptionCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(MyExceptionCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorData() {
        return errorData;
    }

    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }
}
