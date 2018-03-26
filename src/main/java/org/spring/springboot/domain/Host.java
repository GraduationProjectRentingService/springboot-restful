package org.spring.springboot.domain;

/**
 * Created by Administrator on 2018/3/9.
 */
public class Host {

    /**
     * 房东编号
     */
    private Long id;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 密码
     */
    private String password;

    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
