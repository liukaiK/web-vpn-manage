package cn.com.goodlan.webvpn.service.school.user;

import cn.com.goodlan.webvpn.pojo.dto.ChangePasswordDTO;
import cn.com.goodlan.webvpn.pojo.dto.ResetPasswordDTO;
import cn.com.goodlan.webvpn.pojo.dto.UserDTO;
import cn.com.goodlan.webvpn.pojo.vo.SchoolUserVO;
import cn.com.goodlan.webvpn.pojo.vo.SystemUserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchoolUserService {

    Page<SchoolUserVO> search(UserDTO userDTO, Pageable pageable);

    void save(UserDTO userDTO);

    void update(UserDTO userDTO);

    void remove(String ids);

    SystemUserVO getById(Long id);

    /**
     * 重置密码
     *
     * @param resetPasswordDTO
     */
    void resetPassword(ResetPasswordDTO resetPasswordDTO);

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

    /**
     * 修改密码
     */
    void changePassword(ChangePasswordDTO changePasswordDTO);


}
