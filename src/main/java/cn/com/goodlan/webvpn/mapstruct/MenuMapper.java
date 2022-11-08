package cn.com.goodlan.webvpn.mapstruct;

import cn.com.goodlan.webvpn.pojo.entity.system.menu.Menu;
import cn.com.goodlan.webvpn.pojo.vo.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    List<MenuVO> convertList(List<Menu> menuList);

    @Mappings({
            @Mapping(source = "parent.id", target = "parentId"),
            @Mapping(source = "parent.name", target = "parentName")
    })
    MenuVO convert(Menu menu);

}
