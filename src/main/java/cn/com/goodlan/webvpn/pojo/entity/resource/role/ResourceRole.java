package cn.com.goodlan.webvpn.pojo.entity.resource.role;

import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.resource.navigation.Navigation;
import cn.com.goodlan.webvpn.pojo.entity.resource.user.User;
import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
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
public class ResourceRole extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * 备注
     */
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resource_role_navigation", joinColumns = @JoinColumn(name = "navigation_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Navigation> navigations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resource_user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();


    protected ResourceRole() {

    }

    public ResourceRole(Long id) {
        this.id = id;
    }

    public ResourceRole(String name, String description, List<Navigation> navigations) {
        if (StringUtils.isEmpty(name)) {
            throw new BusinessException("角色名称不能为空");
        }
        this.name = name;
        this.description = description;
        this.navigations = navigations;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateNavigations(List<Navigation> navigations) {
        this.navigations = navigations;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Navigation> getNavigations() {
        return navigations;
    }

}
