package com.springboot.map.service;

import com.springboot.map.dto.ApiInfoDto;
import com.springboot.map.repositoy.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:application.yaml")
public class KakaoService {

    @Value("${company.kakao.domain}")
    String kakaoDomain;

    @Autowired
    ApiRepository apiRepository;

    @Transactional(readOnly = true)
    public ApiInfoDto getKakaoApiInfo(){

        ApiInfoDto kakaoApiInfo = apiRepository.findByDomain(kakaoDomain).findFirst().map(ApiInfoDto::new).get();

        return kakaoApiInfo;
    }
}
