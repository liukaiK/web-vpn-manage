package cn.com.goodlan.webvpn.controller.school.user;

import cn.com.goodlan.webvpn.annotations.Create;
import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import cn.com.goodlan.webvpn.pojo.dto.ChangePasswordDTO;
import cn.com.goodlan.webvpn.pojo.dto.ResetPasswordDTO;
import cn.com.goodlan.webvpn.pojo.dto.UpdateProfileDTO;
import cn.com.goodlan.webvpn.pojo.dto.UserDTO;
import cn.com.goodlan.webvpn.pojo.vo.SystemUserVO;
import cn.com.goodlan.webvpn.service.system.role.RoleService;
import cn.com.goodlan.webvpn.service.system.admin.AdminService;
import cn.com.goodlan.webvpn.utils.SecurityUtil;
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
@RequestMapping("/school/user")
public class SchoolUserController {

    @Autowired
    private AdminService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:admin:view')")
    public ModelAndView user() {
        return new ModelAndView("system/user/user");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:admin:search')")
    public Page<SystemUserVO> search(UserDTO userDTO, @PageableDefault Pageable pageable) {
        return userService.search(userDTO, pageable);
    }


    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:admin:add')")
    public ModelAndView add(Model model) {
        model.addAttribute("roles", roleService.selectRoleAll());
        return new ModelAndView("system/user/add");
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('system:admin:add')")
    public void add(@Validated(Create.class) UserDTO userDTO) {
        userService.save(userDTO);
    }


    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:admin:edit')")
    public ModelAndView edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.selectRoleByUser(id));
        return new ModelAndView("system/user/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:admin:edit')")
    public void edit(@Valid UserDTO userDTO) {
        userService.update(userDTO);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:admin:remove')")
    public void remove(String ids) {
        userService.remove(ids);
    }

    /**
     * 跳转到重置密码页面
     */
    @GetMapping("/resetPassword/{id}")
    @PreAuthorize("hasAuthority('system:admin:resetPassword')")
    public ModelAndView resetPassword(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return new ModelAndView("system/user/resetpassword");
    }


    /**
     * 重置密码
     */
    @PostMapping("/resetPassword")
    @PreAuthorize("hasAuthority('system:admin:resetPassword')")
    public void resetPassword(@Valid ResetPasswordDTO resetPasswordDTO) {
        userService.resetPassword(resetPasswordDTO);
    }

    /**
     * 跳转到个人详细信息页面
     */
    @GetMapping("/profile")
    public ModelAndView profile(Model model) {
        model.addAttribute("user", userService.getById(SecurityUtil.getAdminId()));
        return new ModelAndView("system/user/profile/profile");
    }

    /**
     * 修改个人信息
     */
    @PostMapping("/profile/update")
    public void updateProfile(@Valid UpdateProfileDTO updateProfileDTO) {
        userService.updateProfile(updateProfileDTO);
    }


    /**
     * 修改密码
     */
    @PostMapping("/profile/changePassword")
    public void changePassword(@Valid ChangePasswordDTO changePasswordDTO) {
        userService.changePassword(changePasswordDTO);
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
