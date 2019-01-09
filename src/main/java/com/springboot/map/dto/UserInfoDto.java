package com.springboot.map.dto;

import com.springboot.map.entity.UserInfo;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
public class UserInfoDto {

    private int id;

    private String userid;

    private String password;

    private String roles;

    public UserInfo getEntity(){
        return UserInfo.builder()
                .userId(userid)
                .password(password)
                .roles(roles).build();
    }

    public UserInfoDto(){

    }
    public UserInfoDto(UserInfo entity){
        this.id = entity.getId();
        this.userid = entity.getUserid();
        this.password = entity.getPassword();
        this.roles = entity.getRoles();
    }

}
