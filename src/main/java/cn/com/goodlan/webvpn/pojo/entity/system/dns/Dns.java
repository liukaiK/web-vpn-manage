package cn.com.goodlan.webvpn.pojo.entity.system.dns;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * DNS
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "system_dns")
public class Dns extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    protected Dns() {
    }

    public Dns(String ip) {
        this.ip = ip;
    }

    public void updateIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public Long getId() {
        return id;
    }

}