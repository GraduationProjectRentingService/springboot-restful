package org.spring.springboot.exception;

import org.spring.springboot.utils.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class MyExceptionAssert {
    public MyExceptionAssert() {
    }

    public static void isTrue(boolean expression, MyExceptionCode code, String message) {
        isTrue(expression, code, message, (Object)null);
    }

    public static void isTrue(boolean expression, MyExceptionCode code, String message, Object errorData) {
        if(!expression) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void isNull(Object object, MyExceptionCode code, String message) {
        isNull(object, code, message, (Object)null);
    }

    public static void isNull(Object object, MyExceptionCode code, String message, Object errorData) {
        if(object != null) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void isNotNull(Object object, MyExceptionCode code, String message) {
        isNotNull(object, code, message, (Object)null);
    }

    public static void isNotNull(Object object, MyExceptionCode code, String message, Object errorData) {
        if(object == null) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void notNull(Object object, MyExceptionCode code, String message) {
        notNull(object, code, message, (Object)null);
    }

    public static void notNull(Object object, MyExceptionCode code, String message, Object errorData) {
        if(object == null) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void isNotBlank(String text, MyExceptionCode code, String message) {
        isNotBlank(text, code, message, (Object)null);
    }

    public static void isNotBlank(String text, MyExceptionCode code, String message, Object errorData) {
        if(!StringUtils.isNotBlank(text)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void isNotEmpty(String text, MyExceptionCode code, String message) {
        isNotEmpty((String)text, code, message, (Object)null);
    }

    public static void isNotEmpty(String text, MyExceptionCode code, String message, Object errorData) {
        if(StringUtils.isEmpty(text)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void hasValue(Long value, MyExceptionCode code, String message) {
        hasValue((Long)value, code, message, (Object)null);
    }

    public static void hasValue(Long value, MyExceptionCode code, String message, Object errorData) {
        if(value == null || value.equals(Long.valueOf(0L))) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void hasValue(Short value, MyExceptionCode code, String message) {
        hasValue((Short)value, code, message, (Object)null);
    }

    public static void hasValue(Short value, MyExceptionCode code, String message, Object errorData) {
        if(value == null || value.equals(Integer.valueOf(0))) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void hasValue(Integer value, MyExceptionCode code, String message) {
        hasValue((Integer)value, code, message, (Object)null);
    }

    public static void hasValue(Integer value, MyExceptionCode code, String message, Object errorData) {
        if(value == null || value.equals(Integer.valueOf(0))) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, MyExceptionCode code, String message) {
        doesNotContain(textToSearch, substring, code, message, (Object)null);
    }

    public static void doesNotContain(String textToSearch, String substring, MyExceptionCode code, String message, Object errorData) {
        if(!StringUtils.contains(textToSearch, substring)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void isNotEmpty(Object[] array, MyExceptionCode code, String message) {
        isNotEmpty((Object[])array, code, message, (Object)null);
    }

    public static void isNotEmpty(Object[] array, MyExceptionCode code, String message, Object errorData) {
        if(ObjectUtils.isEmpty(array)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void hasNoNullElements(Object[] array, MyExceptionCode code, String message) {
        hasNoNullElements(array, code, message, (Object)null);
    }

    public static void hasNoNullElements(Object[] array, MyExceptionCode code, String message, Object errorData) {
        if(array != null) {
            for(int i = 0; i < array.length; ++i) {
                if(array[i] == null) {
                    throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
                }
            }
        }

    }

    public static void isNotEmpty(Collection<?> collection, MyExceptionCode code, String message) {
        isNotEmpty((Collection)collection, code, message, (Object)null);
    }

    public static void isNotEmpty(Collection<?> collection, MyExceptionCode code, String message, Object errorData) {
        if(CollectionUtils.isEmpty(collection)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void isEmpty(Collection<?> collection, MyExceptionCode code, String message) {
        if(!CollectionUtils.isEmpty(collection)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, (Object)null);
        }
    }

    public static void isEmpty(Collection<?> collection, MyExceptionCode code, String message, Object errorData) {
        if(!CollectionUtils.isEmpty(collection)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void isNotEmpty(Map<?, ?> map, MyExceptionCode code, String message) {
        isNotEmpty((Map)map, code, message, (Object)null);
    }

    public static void isNotEmpty(Map<?, ?> map, MyExceptionCode code, String message, Object errorData) {
        if(CollectionUtils.isEmpty(map)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message, errorData);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, MyExceptionCode code, String message) {
        isInstanceOf(type, obj, code, message, (Object)null);
    }

    public static void isInstanceOf(Class<?> type, Object obj, MyExceptionCode code, String message, Object errorData) {
        notNull(type, code, message + ": type is null");
        if(!type.isInstance(obj)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message + "Object of class [" + (obj != null?obj.getClass().getName():"null") + "] must be an instance of " + type, errorData);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, MyExceptionCode code, String message) {
        isAssignable(superType, subType, code, message, (Object)null);
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, MyExceptionCode code, String message, Object errorData) {
        notNull(superType, code, message + ": superType is null");
        if(subType == null || !superType.isAssignableFrom(subType)) {
            throw new MyException(code, MyExceptionType.BUSINESS, message + subType + " is not assignable to " + superType, errorData);
        }
    }

    public static void isTowValEquals(Object a, Object b, MyExceptionCode code, String message){
        if (!Objects.equals(a, b)){
            throw new MyException(code, MyExceptionType.BUSINESS, message, null);
        }
    }
}
