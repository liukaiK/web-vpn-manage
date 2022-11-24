package cn.com.goodlan.webvpn.pojo.entity.resource.role;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.resource.user.User;
import cn.hutool.core.collection.CollectionUtil;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色实体
 *
 * @author liukai
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "resource_role")
public class Role extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * 备注
     */
    private String remark;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resource_user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 角色下面是否有人
     */
    public boolean hasUser() {
        return CollectionUtil.isNotEmpty(this.users);
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

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> userList) {
        this.users = userList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
