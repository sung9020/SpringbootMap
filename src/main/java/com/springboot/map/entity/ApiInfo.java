package com.springboot.map.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="APIINFO")
@Data
public class ApiInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String domain;

    @Column
    private String appkey;

    @Column
    private String authkeyword;

    @Builder
    public ApiInfo(String domain, String appKey, String authKeyword){
        this.domain =domain;
        this.appkey = appKey;
        this.authkeyword = authKeyword;
    }

    public ApiInfo(int id, String domain, String appKey, String authKeyword){
        this.id = id;
        this.domain = domain;
        this.appkey =appKey;
        this.authkeyword = authKeyword;
    }
}
