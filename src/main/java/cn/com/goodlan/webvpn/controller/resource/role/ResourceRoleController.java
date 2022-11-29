package cn.com.goodlan.webvpn.controller.resource.role;

import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import cn.com.goodlan.webvpn.pojo.dto.ResourceRoleDTO;
import cn.com.goodlan.webvpn.pojo.vo.ResourceRoleVO;
import cn.com.goodlan.webvpn.service.resource.role.ResourceRoleService;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * 角色资源管理Controller
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/resource/role")
public class ResourceRoleController {

    @Autowired
    private ResourceRoleService resourceRoleService;


    @GetMapping
    @PreAuthorize("hasAuthority('resource:role:view')")
    public ModelAndView role() {
        return new ModelAndView("resource/role/role");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('resource:role:search')")
    public Page<ResourceRoleVO> search(String name, @PageableDefault Pageable pageable) {
        return resourceRoleService.search(name, pageable);
    }


    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('resource:role:add')")
    public ModelAndView add() {
        return new ModelAndView("resource/role/add");
    }

    /**
     * 保存角色
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('resource:role:add')")
    public void create(@Valid ResourceRoleDTO roleDTO) {
        resourceRoleService.save(roleDTO);
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('resource:role:edit')")
    public ModelAndView edit(@PathVariable Long id, Model model) {
        model.addAttribute("role", resourceRoleService.getById(id));
        return new ModelAndView("resource/role/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('resource:role:edit')")
    public void edit(@Valid ResourceRoleDTO resourceRoleDTO) {
        resourceRoleService.update(resourceRoleDTO);
    }

    /**
     * 删除角色
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('resource:role:remove')")
    public void remove(String ids) {
        resourceRoleService.remove(ids);
    }


    /**
     * 检查角色名称是否重复
     */
    @PostMapping("/checkRoleNameUnique")
    public boolean checkRoleNameUnique(String roleName, Long roleId) {
        if (ObjectUtil.isEmpty(roleId)) {
            return resourceRoleService.checkRoleNameUnique(roleName);
        } else {
            return resourceRoleService.checkRoleNameUnique(roleId, roleName);
        }
    }

}
