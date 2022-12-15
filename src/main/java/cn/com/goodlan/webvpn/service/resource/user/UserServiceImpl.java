package cn.com.goodlan.webvpn.service.resource.user;

import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.mapstruct.UserMapper;
import cn.com.goodlan.webvpn.pojo.dto.UserDTO;
import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import cn.com.goodlan.webvpn.pojo.entity.resource.user.User;
import cn.com.goodlan.webvpn.pojo.vo.UserVO;
import cn.com.goodlan.webvpn.repository.resource.role.ResourceRoleRepository;
import cn.com.goodlan.webvpn.repository.resource.user.UserRepository;
import cn.hutool.core.convert.Convert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRoleRepository resourceRoleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String userCacheKey = "user";

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

        User user = new User(userDTO.getName(), userDTO.getUsername(), roles);
        userRepository.save(user);

        putUserInCache(user);

    }


    private void putUserInCache(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("username", user.getUsername());
        map.put("roleIds", user.getRoleIds());
        map.put("roleNames", user.getRoleNames());
        try {
            redisTemplate.opsForHash().put(userCacheKey, user.getUsername(), objectMapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            log.error("序列化json失败", e);
            throw new BusinessException("用户保存到缓存失败");
        }
    }

    @Override
    public void update(UserDTO userDTO) {
        Long[] roleIds = Convert.toLongArray(userDTO.getRoleIds());
        List<ResourceRole> roles = resourceRoleRepository.findAllById(Arrays.asList(roleIds));

        User user = userRepository.getReferenceById(userDTO.getId());
        user.updateName(userDTO.getName());

        user.updateRoles(roles);
        userRepository.save(user);

        putUserInCache(user);
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
