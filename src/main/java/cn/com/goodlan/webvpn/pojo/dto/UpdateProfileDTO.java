package cn.com.goodlan.webvpn.pojo.dto;


import cn.com.goodlan.webvpn.annotations.MobileNumber;

import javax.validation.constraints.Email;

/**
 * 修改个人信息DTO
 */
public class UpdateProfileDTO {

    @MobileNumber
    private String phoneNumber;

    @Email
    private String email;

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

}
