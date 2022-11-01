package cn.com.goodlan.webvpn.pojo.entity.systemuser;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 管理员实体
 *
 * @author liukai
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "system_user")
public class SystemUser extends AbstractEntity {

    private final static Long ADMIN_ID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Username username;

    private String name;

//    private Character sex;

//    private String email;

    @Embedded
    private Password password;

//    @Embedded
//    private PhoneNumber phoneNumber;

//    private String remark;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    public SystemUser(Long id) {
        this.id = id;
    }

    public SystemUser() {

    }

//    public void updateEmail(String email) {
//        this.email = email;
//    }

//    public void updateSex(Character sex) {
//        this.sex = sex;
//    }

    public void updateName(String name) {
        this.setName(name);
    }

    public void updateUsername(Username username) {
        this.setUsername(username);
    }

//    public void updatePhoneNumber(PhoneNumber phoneNumber) {
//        this.setPhoneNumber(phoneNumber);
//    }

//    public void updateRemark(String remark) {
//        this.remark = remark;
//    }

    public void updatePassword(Password password) {
        this.password = password;
    }

    /**
     * 判断是否为超级管理员
     */
    public boolean isAdmin() {
        return ADMIN_ID.equals(this.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Username getUsername() {
        return username;
    }

    protected void setUsername(Username username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

//    public Character getSex() {
//        return sex;
//    }

//    public void setSex(Character sex) {
//        this.sex = sex;
//    }

//    public String getEmail() {
//        return email;
//    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

//    public String getRemark() {
//        return remark;
//    }

//    public void setRemark(String remark) {
//        this.remark = remark;
//    }

//    public PhoneNumber getPhoneNumber() {
//        return phoneNumber;
//    }

//    protected void setPhoneNumber(PhoneNumber phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

}