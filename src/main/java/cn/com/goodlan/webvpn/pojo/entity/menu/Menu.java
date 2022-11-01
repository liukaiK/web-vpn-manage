package cn.com.goodlan.webvpn.pojo.entity.menu;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.role.Role;
import cn.hutool.core.collection.CollectionUtil;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体
 *
 * @author liukai
 */
@Entity
@Table(name = "system_menu")
public class Menu extends AbstractEntity {

    public static final String M = "M";

    public static final String C = "C";

    public static final String F = "F";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String authority;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Menu parent;

    private String icon;

    private String visible;

    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单类型 M目录 C菜单 F按钮
     */
    private String menuType;

    private String isRefresh;

    /**
     * 打开方式 页签 或是 新窗口
     */
    private String target;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Menu> children = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_role_menu", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList = new ArrayList<>();

    public Menu() {
    }

    public Menu(Long id) {
        this.id = id;
    }

    /**
     * 是否存在子节点
     */
    public boolean hasChildren() {
        return CollectionUtil.isNotEmpty(this.children);
    }

    /**
     * 是否有父节点
     */
    public boolean hasParent() {
        return this.parent != null;
    }

    /**
     * 添加父节点
     */
    public void addParent(Long parentId) {
        this.parent = new Menu(parentId);
    }

    /**
     * 是否已经分配了角色
     */
    public boolean hasRole() {
        return CollectionUtil.isNotEmpty(this.roleList);
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            this.url = "#";
        } else {
            this.url = url;
        }
    }


    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(String isRefresh) {
        this.isRefresh = isRefresh;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        //提高代码健壮性,不是同一个类型就直接返回false,省得向下转型了
//        if (this.getClass() != obj.getClass()) {
//            return false;
//        }
//        Menu menu = (Menu) obj;
//        return this.id.equals(menu.getId());
//    }
//
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }


}
