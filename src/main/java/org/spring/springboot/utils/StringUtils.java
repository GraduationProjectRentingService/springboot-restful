package org.spring.springboot.utils;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean contains(CharSequence seq, CharSequence searchSeq) {
        if (seq != null && searchSeq != null) {
            return indexOf(seq, searchSeq, 0) >= 0;
        } else {
            return false;
        }
    }

    static int indexOf(CharSequence cs, CharSequence searchChar, int start) {
        return cs.toString().indexOf(searchChar.toString(), start);
    }

    /**
     * 判断是否为有效合理的手机号码
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum){
//        String regex_1 = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";//手机号码
        String regex = "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|7[0678])\\d{8}$";//手机号码
        return Pattern.compile(regex).matcher(phoneNum).matches();
//        return true;
    }


    /**
     * 判断身份证格式是否合法
     * @param idCard
     * @return
     */
    public static boolean isIDCardLegitimate(String idCard){
        String regex_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        String regex_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
        return Pattern.compile(regex_15).matcher(idCard).matches() || Pattern.compile(regex_18).matcher(idCard).matches();
    }


}
