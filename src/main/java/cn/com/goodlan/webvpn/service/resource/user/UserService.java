package cn.com.goodlan.webvpn.service.resource.user;

import cn.com.goodlan.webvpn.pojo.dto.UserDTO;
import cn.com.goodlan.webvpn.pojo.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserVO> search(UserDTO userDTO, Pageable pageable);

    void save(UserDTO userDTO);

    void update(UserDTO userDTO);

    void remove(String ids);

    UserVO getById(Long id);

    /**
     * 检查账号是否已经存在
     *
     * @param username 账号
     * @return 是否存在
     */
    boolean checkUsernameUnique(String username);

    /**
     * 检查账号是否已经存在, 排除userId
     *
     * @param userId   用户ID
     * @param username 账号
     * @return 是否存在
     */
    boolean checkUsernameUnique(Long userId, String username);


}
