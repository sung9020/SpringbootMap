package com.springboot.map.service.factory;

import com.springboot.map.entity.ApiInfo;
import com.springboot.map.repositoy.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApiInfoFactory {

    @Autowired
    ApiRepository apiRepository;

    public ApiInfoFactory(){

    }

    public ApiInfo createApiInfo(String domain){

       ApiInfo mapApi = apiRepository.findByDomain(domain);
       return mapApi;
    }


}
