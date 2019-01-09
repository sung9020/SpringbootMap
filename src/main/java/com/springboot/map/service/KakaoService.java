package com.springboot.map.service;

import com.springboot.map.dto.ApiInfoDto;
import com.springboot.map.entity.ApiInfo;
import com.springboot.map.repositoy.ApiRepository;
import com.springboot.map.service.factory.ApiInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yaml")
public class KakaoService {

    @Value("${company.kakao.domain}")
    String kakaoDomain;

    @Autowired
    ApiRepository apiRepository;

    private ApiInfoFactory apiInfoFactoryBuilder(){
        return new ApiInfoFactory(apiRepository);
    }

    public ApiInfoDto getKakaoApiInfo(){

        ApiInfoDto kakaoApiInfo = apiInfoFactoryBuilder().createApiInfo(kakaoDomain);

        return kakaoApiInfo;
    }
}
