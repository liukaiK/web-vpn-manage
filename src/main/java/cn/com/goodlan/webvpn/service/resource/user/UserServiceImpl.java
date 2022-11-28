package cn.com.goodlan.webvpn.service.resource.user;

import cn.com.goodlan.webvpn.mapstruct.UserMapper;
import cn.com.goodlan.webvpn.pojo.dto.UserDTO;
import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import cn.com.goodlan.webvpn.pojo.entity.resource.user.User;
import cn.com.goodlan.webvpn.pojo.vo.UserVO;
import cn.com.goodlan.webvpn.repository.resource.role.ResourceRoleRepository;
import cn.com.goodlan.webvpn.repository.resource.user.UserRepository;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRoleRepository resourceRoleRepository;

    @Override
    public Page<UserVO> search(UserDTO userDTO, Pageable pageable) {
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(userDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), userDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(userDTO.getUsername())) {
                list.add(criteriaBuilder.like(root.get("username").as(String.class), userDTO.getUsername() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<User> page = userRepository.findAll(specification, pageable);
        List<UserVO> list = UserMapper.INSTANCE.convert(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(UserDTO userDTO) {
        Long[] roleIds = Convert.toLongArray(userDTO.getRoleIds());
        List<ResourceRole> roles = resourceRoleRepository.findAllById(Arrays.asList(roleIds));

        User user = new User(userDTO.getName(), userDTO.getUsername());
        user.refreshRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = userRepository.getReferenceById(userDTO.getId());
        user.updateName(userDTO.getName());
        Long[] roleIds = Convert.toLongArray(userDTO.getRoleIds());
        List<ResourceRole> roles = resourceRoleRepository.findAllById(Arrays.asList(roleIds));
        user.refreshRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void remove(String ids) {
        Long[] userIds = Convert.toLongArray(ids);
        userRepository.deleteAllById(Arrays.asList(userIds));
    }

    @Override
    public UserVO getById(Long id) {
        User user = userRepository.getReferenceById(id);
        return UserMapper.INSTANCE.convert(user);
    }

    @Override
    public boolean checkUsernameUnique(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkUsernameUnique(Long userId, String username) {
        return userRepository.existsByUsernameAndIdNot(username, userId);
    }


}
