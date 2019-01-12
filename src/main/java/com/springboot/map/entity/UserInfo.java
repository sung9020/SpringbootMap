package com.springboot.map.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="USERS")
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(unique = true)
    String userid;

    @Column
    String password;

    @Column(nullable = false)
    String roles;

    @Builder
    public UserInfo(String userId, String password, String roles){
        this.userid =userId;
        this.password = password;
        this.roles = roles;
    }

    public UserInfo(int id, String userId, String password, String roles){
        this.id = id;
        this.userid = userId;
        this.password =password;
        this.roles = roles;
    }
}
