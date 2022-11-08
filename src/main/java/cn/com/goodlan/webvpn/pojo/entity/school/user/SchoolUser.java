package cn.com.goodlan.webvpn.pojo.entity.school.user;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.system.role.SystemRole;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Password;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 学校的用户
 *
 * @author liukai
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "school_user")
public class SchoolUser extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

//    @Embedded
//    private Password password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "school_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<SystemRole> roles = new ArrayList<>();

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

//    private String email;

//    @Embedded
//    private PhoneNumber phoneNumber;

//    private String remark;


    public SchoolUser(Long id) {
        this.id = id;
    }

    public SchoolUser() {

    }

//    public void updateEmail(String email) {
//        this.email = email;
//    }

    public void updateName(String name) {
        this.setName(name);
    }


//    public void updatePhoneNumber(PhoneNumber phoneNumber) {
//        this.setPhoneNumber(phoneNumber);
//    }

//    public void updateRemark(String remark) {
//        this.remark = remark;
//    }

//    public void updatePassword(Password password) {
//        this.setPassword(password);
//    }

    public void removeAllRole() {
        this.roles = new ArrayList<>();
    }

    public void addRole(SystemRole role) {
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

//    public Password getPassword() {
//        return password;
//    }

//    protected void setPassword(Password password) {
//        this.password = password;
//    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
