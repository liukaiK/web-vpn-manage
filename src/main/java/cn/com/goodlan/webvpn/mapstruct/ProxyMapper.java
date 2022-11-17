package cn.com.goodlan.webvpn.mapstruct;

import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;
import cn.com.goodlan.webvpn.pojo.vo.ProxyVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProxyMapper {

    ProxyMapper INSTANCE = Mappers.getMapper(ProxyMapper.class);

    @Mappings({
//            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber"),
    })
    List<ProxyVO> convert(List<Proxy> proxy);

    ProxyVO convert(Proxy proxy);

}
