package cn.com.goodlan.webvpn.pojo.entity.resource.user;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
@Table(name = "resource_user")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

//    @Embedded
//    private Password password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resource_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<ResourceRole> roles = new ArrayList<>();

//    private String email;

//    @Embedded
//    private PhoneNumber phoneNumber;

//    private String remark;


    public User(Long id) {
        this.id = id;
    }

    public User() {

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

    public void addRole(ResourceRole role) {
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

    public List<ResourceRole> getRoles() {
        return roles;
    }

    protected void setRoles(List<ResourceRole> roleList) {
        this.roles = roleList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
