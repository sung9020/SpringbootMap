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

import java.io.UnsupportedEncodingException;
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

    private String makeParam(RequestDto requestDto) throws UnsupportedEncodingException {

        Map<String, Object> reuqestMap = mapper.convertValue(requestDto, Map.class);
        StringBuffer paramText = new StringBuffer();


            reuqestMap.forEach((key,value) ->{
                paramText.append(key);
                paramText.append("=");
                try {
                    paramText.append(URLEncoder.encode(String.valueOf(value), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                paramText.append("&");
            });


        //delete &
        paramText.setLength(paramText.length()-1);
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
        String requestParameter = requestParameterBuilder.toString();

        //make author keyword
        String authorization = makeAuthorization(kakaoApiInfo.getAuthKeyword(), kakaoApiInfo.getAppKey());

        result = WebUtils.httpRequest("GET", requestParameter , authorization);

        if(!result.isEmpty()){
            apiResponseDto = mapper.readValue(result, ResponseDto.class);
        }


        return apiResponseDto;
    }


}
