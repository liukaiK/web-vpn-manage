package cn.com.goodlan.webvpn.pojo.dto;


import cn.com.goodlan.webvpn.annotations.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AdminDTO {

    private Long id;

    private String name;

    /**
     * 只有新增用户的时候  才需要校验账号
     */
    @Username(groups = Create.class)
    private String username;

    /**
     * 只有新增用户的时候  才需要校验密码
     */
    @Password(groups = Create.class)
    private String password;

    @Email
    private String email;

    @MobileNumber
    private String phoneNumber;

    @Sex
    private Character sex;

    @NotBlank(message = "请选择学院")
    private String collegeId;

    private String roleIds;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
