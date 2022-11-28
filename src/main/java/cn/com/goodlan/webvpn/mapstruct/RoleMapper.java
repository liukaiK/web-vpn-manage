package cn.com.goodlan.webvpn.mapstruct;

import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import cn.com.goodlan.webvpn.pojo.entity.system.role.SystemRole;
import cn.com.goodlan.webvpn.pojo.vo.ResourceRoleVO;
import cn.com.goodlan.webvpn.pojo.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    List<RoleVO> convert(List<SystemRole> roleList);

    RoleVO convert(SystemRole role);


    List<ResourceRoleVO> convertResourceRole(List<ResourceRole> roles);

    ResourceRoleVO convert(ResourceRole role);

}
