package cn.com.goodlan.webvpn.mapstruct;

import cn.com.goodlan.webvpn.pojo.entity.resource.navigation.Navigation;
import cn.com.goodlan.webvpn.pojo.vo.NavigationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NavigationMapper {

    NavigationMapper INSTANCE = Mappers.getMapper(NavigationMapper.class);

    List<NavigationVO> convert(List<Navigation> navigations);

    NavigationVO convert(Navigation navigation);

}
