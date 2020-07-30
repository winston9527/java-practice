package com.winston.practice.jdk.proxy.entity;

/**
 * @Description:用户信息
 * @Author Winston
 * @Version 1.0 2018年9月11日 下午2:31:53
 */
public class User {

    private long userId;

    private String userName;

    private String realName;

    private String phone;


    public long getUserId() {
        return userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getRealName() {
        return realName;
    }


    public void setRealName(String realName) {
        this.realName = realName;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", realName=" + realName + ", phone=" + phone + "]";
    }


}
