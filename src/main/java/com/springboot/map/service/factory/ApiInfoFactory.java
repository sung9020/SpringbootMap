package com.springboot.map.service.factory;

import com.springboot.map.dto.ApiInfoDto;
import com.springboot.map.dto.UserInfoDto;
import com.springboot.map.entity.ApiInfo;
import com.springboot.map.repositoy.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class ApiInfoFactory {

    ApiRepository apiRepository;

    public ApiInfoFactory(ApiRepository apiRepository){
        this.apiRepository = apiRepository;
    }

    @Transactional
    public ApiInfoDto createApiInfo(String domain){
       ApiInfoDto mapApi = apiRepository.findByDomain(domain).findFirst().map(ApiInfoDto::new).get();
       return mapApi;
    }


}
