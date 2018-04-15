package org.spring.springboot.dto;

import org.spring.springboot.domain.CheckInPeople;

public class CheckInPeopleDto extends CheckInPeople {
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

    private String phoneNumber;
    private String token;
}
