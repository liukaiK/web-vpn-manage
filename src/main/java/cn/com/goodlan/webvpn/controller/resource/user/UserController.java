package cn.com.goodlan.webvpn.controller.resource.user;

import cn.com.goodlan.webvpn.annotations.Create;
import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import cn.com.goodlan.webvpn.pojo.dto.UserDTO;
import cn.com.goodlan.webvpn.pojo.vo.UserVO;
import cn.com.goodlan.webvpn.service.resource.role.ResourceRoleService;
import cn.com.goodlan.webvpn.service.resource.user.UserService;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * 学校用户控制器
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/resource/user")
public class UserController {

    @Autowired
    private ResourceRoleService resourceRoleService;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('resource:user:view')")
    public ModelAndView user() {
        return new ModelAndView("resource/user/user");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('resource:user:search')")
    public Page<UserVO> search(UserDTO userDTO, @PageableDefault Pageable pageable) {
        return userService.search(userDTO, pageable);
    }


    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('resource:user:add')")
    public ModelAndView add(Model model) {
        model.addAttribute("roles", resourceRoleService.selectRoleAll());
        return new ModelAndView("resource/user/add");
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('system:user:add')")
    public void add(@Validated(Create.class) UserDTO userDTO) {
        userService.save(userDTO);
    }


    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('resource:user:edit')")
    public ModelAndView edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", resourceRoleService.selectRoleByUser(id));
        return new ModelAndView("resource/user/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('resource:user:edit')")
    public void edit(@Valid UserDTO userDTO) {
        userService.update(userDTO);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('resource:user:remove')")
    public void remove(String ids) {
        userService.remove(ids);
    }


    /**
     * 校验账号是否已经存在
     */
    @PostMapping("/checkUsernameUnique")
    public boolean checkUsernameUnique(Long userId, String username) {
        if (ObjectUtil.isEmpty(userId)) {
            return userService.checkUsernameUnique(username);
        } else {
            return userService.checkUsernameUnique(userId, username);
        }
    }

}
