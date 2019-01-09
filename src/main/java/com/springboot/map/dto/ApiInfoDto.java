package com.springboot.map.dto;

import com.springboot.map.entity.ApiInfo;
import lombok.Data;

@Data
public class ApiInfoDto {

    private int id;


    private String domain;

    private String appKey;

    private String authKeyword;

    public ApiInfo getEntity(){
        return ApiInfo.builder()
                .domain(domain)
                .appKey(appKey)
                .authKeyword(authKeyword)
                .build();
    }

    public ApiInfoDto(ApiInfo entity){
        this.id = entity.getId();
        this.domain = entity.getDomain();
        this.appKey = entity.getAppkey();
        this.authKeyword = entity.getAuthkeyword();
    }
}
