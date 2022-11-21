package cn.com.goodlan.webvpn.pojo.entity.system.user;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.system.role.SystemRole;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员实体
 *
 * @author liukai
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "system_admin")
public class Admin extends AbstractEntity {

    private final static Long ADMIN_ID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    @Embedded
    private Username username;

    @Embedded
    private Password password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_admin_role", joinColumns = @JoinColumn(name = "admin_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<SystemRole> roles = new ArrayList<>();

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

//    private String email;

//    @Embedded
//    private PhoneNumber phoneNumber;

//    private String remark;


    public Admin(Long id) {
        this.id = id;
    }

    public Admin() {

    }

//    public void updateEmail(String email) {
//        this.email = email;
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
        this.setPassword(password);
    }

    public void removeAllRole() {
        this.roles = new ArrayList<>();
    }

    public void addRole(SystemRole role) {
        roles.add(role);
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

    protected void setPassword(Password password) {
        this.password = password;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    protected void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<SystemRole> getRoles() {
        return roles;
    }

    protected void setRoles(List<SystemRole> roleList) {
        this.roles = roleList;
    }

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
