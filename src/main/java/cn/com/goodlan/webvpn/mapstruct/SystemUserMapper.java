package cn.com.goodlan.webvpn.mapstruct;

import cn.com.goodlan.webvpn.pojo.entity.system.user.Admin;
import cn.com.goodlan.webvpn.pojo.vo.AdminVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SystemUserMapper {

    SystemUserMapper INSTANCE = Mappers.getMapper(SystemUserMapper.class);

    List<AdminVO> convert(List<Admin> users);

    @Mappings({
            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber"),
//            @Mapping(source = "college.name", target = "collegeName"),
            @Mapping(source = "username.username", target = "username"),
//            @Mapping(source = "college.id", target = "collegeId")
    })
    AdminVO convert(Admin admin);

}