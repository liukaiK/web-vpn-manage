package cn.com.goodlan.webvpn.pojo.dto;


import cn.com.goodlan.webvpn.annotations.MobileNumber;
import cn.com.goodlan.webvpn.annotations.Sex;

import javax.validation.constraints.Email;

/**
 * 修改个人信息DTO
 */
public class UpdateProfileDTO {

    @MobileNumber
    private String phoneNumber;

    @Email
    private String email;

    @Sex
    private Character sex;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }
}
