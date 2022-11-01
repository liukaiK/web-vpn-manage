package cn.com.goodlan.webvpn.security.web;

import java.io.Serializable;

/**
 * 存放在SpringSecurity中的实体部门对象
 *
 * @author liukai
 */
public class SecurityCollegeBean implements Serializable {

    private String id;

    private String collegeName;

//    private SecurityCollegeBean(College college) {
//        this.id = college.getId();
//        this.collegeName = college.getName();
//    }

//    public static SecurityCollegeBean convertFromCollege(College college) {
//        return new SecurityCollegeBean(college);
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
