package org.spring.springboot.dto;

public class LikeHouseDto implements IBaseDto{
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String phoneNumber;
    private String token;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    private int houseId;

    @Override
    public String toString() {
        return "LikeHouseDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", token='" + token + '\'' +
                ", houseId=" + houseId +
                '}';
    }
}
