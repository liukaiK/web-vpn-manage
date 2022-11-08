package cn.com.goodlan.webvpn.service.systemuser;


import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.mapstruct.UserMapper;
import cn.com.goodlan.webvpn.pojo.dto.ChangePasswordDTO;
import cn.com.goodlan.webvpn.pojo.dto.ResetPasswordDTO;
import cn.com.goodlan.webvpn.pojo.dto.UpdateProfileDTO;
import cn.com.goodlan.webvpn.pojo.dto.UserDTO;
import cn.com.goodlan.webvpn.pojo.entity.role.Role;
import cn.com.goodlan.webvpn.pojo.entity.systemuser.Password;
import cn.com.goodlan.webvpn.pojo.entity.systemuser.SystemUser;
import cn.com.goodlan.webvpn.pojo.entity.systemuser.Username;
import cn.com.goodlan.webvpn.pojo.vo.UserVO;
import cn.com.goodlan.webvpn.repository.systemuser.SystemUserRepository;
import cn.com.goodlan.webvpn.utils.AESUtil;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private SystemUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<UserVO> search(UserDTO userDTO, Pageable pageable) {
        Specification<SystemUser> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(userDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), userDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(userDTO.getUsername())) {
                list.add(criteriaBuilder.like(root.get("username").get("username").as(String.class), userDTO.getUsername() + "%"));
            }
            if (StringUtils.isNotEmpty(userDTO.getPhoneNumber())) {
                list.add(criteriaBuilder.equal(root.get("phoneNumber").as(String.class), AESUtil.encrypt(userDTO.getPhoneNumber())));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<SystemUser> page = userRepository.findAll(specification, pageable);
        List<UserVO> list = UserMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(UserDTO userDTO) {
        SystemUser user = new SystemUser();
        user.updateName(userDTO.getName());
//        user.updateEmail(userDTO.getEmail());
        user.updateUsername(new Username(userDTO.getUsername()));
//        user.updatePhoneNumber(new PhoneNumber(userDTO.getPhoneNumber()));
//        user.updateSex(userDTO.getSex());
//        user.updateRemark(userDTO.getRemark());
        user.updatePassword(new Password(userDTO.getPassword()));
        Long[] roleIds = Convert.toLongArray(userDTO.getRoleIds());
        for (Long roleId : roleIds) {
            user.addRole(new Role(roleId));
        }
        userRepository.save(user);
    }

    @Override
    public void update(UserDTO userDTO) {
        SystemUser user = userRepository.getById(userDTO.getId());
        user.updateName(userDTO.getName());
//        user.updateRemark(userDTO.getRemark());
//        user.updateSex(userDTO.getSex());
//        user.updateEmail(userDTO.getEmail());
//        user.updatePhoneNumber(new PhoneNumber(userDTO.getPhoneNumber()));
//        user.addCollege(userDTO.getCollegeId());
        Long[] roleIds = Convert.toLongArray(userDTO.getRoleIds());
        user.removeAllRole();
        for (Long roleId : roleIds) {
            user.addRole(new Role(roleId));
        }
        userRepository.save(user);
    }

    @Override
    public void remove(String ids) {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds) {
            SystemUser user = new SystemUser(userId);
            if (user.isAdmin()) {
                throw new BusinessException("超级管理员不能被删除");
            }
            userRepository.delete(user);
        }
    }

    @Override
    public UserVO getById(Long id) {
        SystemUser user = userRepository.getById(id);
        return UserMapper.INSTANCE.convert(user);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        SystemUser user = userRepository.getById(resetPasswordDTO.getId());
        user.updatePassword(new Password(resetPasswordDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateProfile(UpdateProfileDTO updateProfileDTO) {
        SystemUser user = userRepository.getById(SecurityUtil.getUserId());
//        user.updateEmail(updateProfileDTO.getEmail());
//        user.updateSex(updateProfileDTO.getSex());
//        user.updatePhoneNumber(new PhoneNumber(updateProfileDTO.getPhoneNumber()));
        userRepository.save(user);
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
        SystemUser user = userRepository.getById(SecurityUtil.getUserId());
        if (passwordIsError(changePasswordDTO, user)) {
            throw new BusinessException("原密码错误!");
        }
        if (!StringUtils.equals(changePasswordDTO.getNewPassword(), changePasswordDTO.getConfirmPassword())) {
            throw new BusinessException("确认密码与新密码不一致!");
        }
        user.updatePassword(new Password(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    private boolean passwordIsError(ChangePasswordDTO changePasswordDTO, SystemUser user) {
        return !passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword().getPassword());
    }

}
