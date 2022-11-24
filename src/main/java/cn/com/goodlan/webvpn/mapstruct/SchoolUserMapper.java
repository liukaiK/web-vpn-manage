package cn.com.goodlan.webvpn.mapstruct;

import cn.com.goodlan.webvpn.pojo.entity.resource.user.User;
import cn.com.goodlan.webvpn.pojo.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SchoolUserMapper {

    SchoolUserMapper INSTANCE = Mappers.getMapper(SchoolUserMapper.class);

    List<UserVO> convert(List<User> users);

    @Mappings({
//            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber"),
//            @Mapping(source = "college.name", target = "collegeName"),
            @Mapping(source = "username", target = "username"),
//            @Mapping(source = "college.id", target = "collegeId")
    })
    UserVO convert(User schoolUser);

}