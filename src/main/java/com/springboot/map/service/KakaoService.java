package com.springboot.map.service;

import com.springboot.map.entity.ApiInfo;
import com.springboot.map.entity.Response;
import com.springboot.map.service.factory.ApiInfoFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaoService {

    @Value("kakao.domain")
    String kakaoDomain;

    private ApiInfoFactory apiInfoFactoryBuilder(){
        return new ApiInfoFactory();
    }

    public ApiInfo getKakaoApiInfo(){

        ApiInfo kakaoApiInfo = apiInfoFactoryBuilder().createApiInfo(kakaoDomain);

        return kakaoApiInfo;
    }
}
