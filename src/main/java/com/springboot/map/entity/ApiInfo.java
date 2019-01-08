package com.springboot.map.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="ApiInfo")
@Data
public class ApiInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int index;

    @Column
    private String domain;

    @Column
    private String appKey;

    @Column
    private String authKeyword;
}
