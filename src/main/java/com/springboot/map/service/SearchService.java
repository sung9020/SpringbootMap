package com.springboot.map.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.map.dto.ApiInfoDto;
import com.springboot.map.entity.ApiInfo;
import com.springboot.map.dto.RequestDto;
import com.springboot.map.dto.ResponseDto;
import com.springboot.map.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    KakaoService kakaoService;

    ObjectMapper mapper;

    public SearchService(){
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);

    }

    private String getFullUrl(String Domain, String url){
        String result = "";
        result = "https://" + Domain + url;

        return result;
    }

    private String makeParam(RequestDto requestDto){

        Map<String, Object> reuqestMap = mapper.convertValue(requestDto, Map.class);
        StringBuilder paramText = new StringBuilder();
        paramText.append("?");

        reuqestMap.forEach((key,value) ->{
            paramText.append(key);
            paramText.append("=");
            paramText.append(value);
            paramText.append("&");
        });

        if(paramText.length() > 1)
        paramText.substring(paramText.length() - 1);

        return paramText.toString();
    }

    private String makeAuthorization(String authKeyword, String appkey) {
        String result = "";
        result = authKeyword + " " + appkey;

        return result;
    }

    public ResponseDto getKakaoKeywordData(RequestDto requestDto) throws Exception{

        String url = "/v2/local/search/keyword.json?";
        String result ="";
        ResponseDto apiResponseDto = new ResponseDto();

        ApiInfoDto kakaoApiInfo = kakaoService.getKakaoApiInfo();

        StringBuilder requestParameterBuilder = new StringBuilder();

        requestParameterBuilder
                .append(getFullUrl(kakaoApiInfo.getDomain(), url))
                .append(makeParam(requestDto));

        //make query
        String requestParameter = URLEncoder.encode(requestParameterBuilder.toString(), "UTF-8");

        //make author keyword
        String authorization = makeAuthorization(kakaoApiInfo.getAuthKeyword(), kakaoApiInfo.getAppKey());

        result = WebUtils.httpRequest("GET", requestParameter , authorization);
        apiResponseDto = mapper.readValue(result, ResponseDto.class);

        return apiResponseDto;
    }


}
