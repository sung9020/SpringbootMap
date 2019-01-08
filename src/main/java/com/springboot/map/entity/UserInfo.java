package com.springboot.map.entity;

import lombok.Data;
import net.bytebuddy.asm.Advice;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name ="Users")
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int index;

    @Column
    String userId;

    @Column
    String password;

    @Column(nullable = false)
    List<String> role;

    public List<GrantedAuthority> getAuthrity(){
        String[] authList = role.toArray(new String[0]);
        return AuthorityUtils.createAuthorityList(authList);
    }
}
