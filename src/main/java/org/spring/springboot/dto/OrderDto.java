package org.spring.springboot.dto;

import org.spring.springboot.domain.Order;

import java.util.List;

public class OrderDto extends Order {
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String phoneNumber;//用户账号
    private String token;//用户登录token

}
