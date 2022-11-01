package cn.com.goodlan.webvpn.pojo.entity.role;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.menu.Menu;
import cn.com.goodlan.webvpn.pojo.entity.systemuser.SystemUser;
import cn.hutool.core.collection.CollectionUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色实体
 *
 * @author liukai
 */
@Entity
@Table(name = "system_role")
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
    @JoinTable(name = "system_user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<SystemUser> userList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private List<Menu> menuList = new ArrayList<>();


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
        return CollectionUtil.isNotEmpty(this.userList);
    }

    public void addMenu(Menu menu) {
        menuList.add(menu);
    }

    public void removeAllMenu() {
        this.menuList = new ArrayList<>();
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


    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }


    public List<SystemUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SystemUser> userList) {
        this.userList = userList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
