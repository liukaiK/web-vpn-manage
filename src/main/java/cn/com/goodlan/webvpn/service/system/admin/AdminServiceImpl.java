package cn.com.goodlan.webvpn.service.system.admin;


import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.mapstruct.SystemUserMapper;
import cn.com.goodlan.webvpn.pojo.dto.AdminDTO;
import cn.com.goodlan.webvpn.pojo.dto.ChangePasswordDTO;
import cn.com.goodlan.webvpn.pojo.dto.ResetPasswordDTO;
import cn.com.goodlan.webvpn.pojo.dto.UpdateProfileDTO;
import cn.com.goodlan.webvpn.pojo.entity.system.role.SystemRole;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Admin;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Password;
import cn.com.goodlan.webvpn.pojo.entity.system.user.PhoneNumber;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Username;
import cn.com.goodlan.webvpn.pojo.vo.AdminVO;
import cn.com.goodlan.webvpn.repository.system.admin.AdminRepository;
import cn.com.goodlan.webvpn.utils.SecurityUtil;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<AdminVO> search(AdminDTO adminDTO, Pageable pageable) {
        Specification<Admin> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(adminDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), adminDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(adminDTO.getUsername())) {
                list.add(criteriaBuilder.like(root.get("username").get("username").as(String.class), adminDTO.getUsername() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<Admin> page = userRepository.findAll(specification, pageable);
        List<AdminVO> list = SystemUserMapper.INSTANCE.convert(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(AdminDTO adminDTO) {
        Admin user = new Admin();
        user.updateName(adminDTO.getName());
        user.updateUsername(new Username(adminDTO.getUsername()));
        user.updatePassword(new Password(adminDTO.getPassword()));
        Long[] roleIds = Convert.toLongArray(adminDTO.getRoleIds());
        for (Long roleId : roleIds) {
            user.addRole(new SystemRole(roleId));
        }
        userRepository.save(user);
    }

    @Override
    public void update(AdminDTO adminDTO) {
        Admin admin = userRepository.getReferenceById(adminDTO.getId());
        admin.updateName(adminDTO.getName());
//        admin.updateRemark(adminDTO.getRemark());
//        admin.updateSex(adminDTO.getSex());
//        admin.updateEmail(adminDTO.getEmail());
//        admin.updatePhoneNumber(new PhoneNumber(adminDTO.getPhoneNumber()));
//        admin.addCollege(adminDTO.getCollegeId());
        Long[] roleIds = Convert.toLongArray(adminDTO.getRoleIds());
        admin.removeAllRole();
        for (Long roleId : roleIds) {
            admin.addRole(new SystemRole(roleId));
        }
        userRepository.save(admin);
    }

    @Override
    public void remove(String ids) {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds) {
            Admin user = new Admin(userId);
            if (user.isAdmin()) {
                throw new BusinessException("超级管理员不能被删除");
            }
            userRepository.delete(user);
        }
    }

    @Override
    public AdminVO getById(Long id) {
        Admin admin = userRepository.getReferenceById(id);
        return SystemUserMapper.INSTANCE.convert(admin);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        Admin user = userRepository.getReferenceById(resetPasswordDTO.getId());
        user.updatePassword(new Password(resetPasswordDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateProfile(UpdateProfileDTO updateProfileDTO) {
        Admin admin = userRepository.getReferenceById(SecurityUtil.getAdminId());
        admin.updateEmail(updateProfileDTO.getEmail());
//        admin.updateSex(updateProfileDTO.getSex());
        admin.updatePhoneNumber(new PhoneNumber(updateProfileDTO.getPhoneNumber()));
        userRepository.save(admin);
    }


    @Override
    public boolean checkUsernameUnique(String username) {
        return userRepository.existsByUsername(new Username(username));
    }

    @Override
    public boolean checkUsernameUnique(Long userId, String username) {
        return userRepository.existsByUsernameAndIdNot(new Username(username), userId);
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        Admin user = userRepository.getReferenceById(SecurityUtil.getAdminId());
        if (passwordIsError(changePasswordDTO, user)) {
            throw new BusinessException("原密码错误!");
        }
        if (!StringUtils.equals(changePasswordDTO.getNewPassword(), changePasswordDTO.getConfirmPassword())) {
            throw new BusinessException("确认密码与新密码不一致!");
        }
        user.updatePassword(new Password(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    private boolean passwordIsError(ChangePasswordDTO changePasswordDTO, Admin user) {
        return !passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword().getPassword());
    }

}
