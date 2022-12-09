package cn.com.goodlan.webvpn.pojo.entity.resource.user;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resource_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<ResourceRole> roles = new ArrayList<>();

    /**
     * 冗余字段 以逗号分割
     */
    private String roleIds;

    /**
     * 冗余字段 以逗号分割
     */
    private String roleNames;

    protected User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String name, String username, List<ResourceRole> roles) {
        this.name = name;
        this.username = username;
        this.roles = roles;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateRoles(List<ResourceRole> roles) {
        clearRole();
        if (CollectionUtil.isNotEmpty(roles)) {
            this.roles.addAll(roles);
            this.roleIds = StringUtils.join(roles.stream().map(ResourceRole::getId).collect(Collectors.toList()), "/");
            this.roleNames = StringUtils.join(roles.stream().map(ResourceRole::getName).collect(Collectors.toList()), "/");
        }
    }

    private void clearRole() {
        this.roleIds = "";
        this.roleNames = "";
        this.roles.clear();
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

    public List<ResourceRole> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

}
