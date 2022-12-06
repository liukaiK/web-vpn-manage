package cn.com.goodlan.webvpn.mapstruct;

import cn.com.goodlan.webvpn.pojo.entity.system.dns.Dns;
import cn.com.goodlan.webvpn.pojo.vo.DnsVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DnsMapper {

    DnsMapper INSTANCE = Mappers.getMapper(DnsMapper.class);

    List<DnsVO> convert(List<Dns> dns);

    @Mappings({
//            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber"),
//            @Mapping(source = "college.name", target = "collegeName"),
//            @Mapping(source = "username", target = "username"),
//            @Mapping(source = "college.id", target = "collegeId")
    })
    DnsVO convert(Dns dns);

}