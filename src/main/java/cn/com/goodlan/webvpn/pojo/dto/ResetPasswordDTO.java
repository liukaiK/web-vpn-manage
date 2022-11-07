package cn.com.goodlan.webvpn.pojo.dto;


import cn.com.goodlan.webvpn.annotations.Password;

public class ResetPasswordDTO {

    private Long id;

    @Password
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
