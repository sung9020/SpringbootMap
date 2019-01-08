package com.springboot.map.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.map.entity.ApiInfo;
import com.springboot.map.entity.Request;
import com.springboot.map.entity.Response;
import com.springboot.map.utils.WebUtils;
import org.hibernate.tool.schema.internal.exec.GenerationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Optional;

@Service
public class SearchService {

    @Autowired
    KakaoService kakaoService;

    ObjectMapper mapper = new ObjectMapper();

    private String getFullUrl(String Domain, String url){
        String result ="";

        result = "https://" + Domain + url;

        return result;
    }

    private String makeParam(Request request){
        /*StringBuilder paramText = new StringBuilder();
        paramText.append("?");
        paramText.append(request.getQuery().);
        paramText.append(request.getQuery().);
        paramText*/
        return "";
    }

    private String makeAuthorization(String authKeyword, String appkey) {
        String result = "";
        result = authKeyword + " " + appkey;

        return result;
    }

    public Response getKakaoKeywordData(Request request) throws Exception{

        String url = "/v2/local/search/keyword.json?";
        String result ="";
        Response apiResponse;

        ApiInfo kakaoApiInfo = kakaoService.getKakaoApiInfo();

        String requestUrl = getFullUrl(kakaoApiInfo.getDomain(),url) + makeParam(request);
        String authorization = makeAuthorization(kakaoApiInfo.getAuthKeyword(), kakaoApiInfo.getAppKey());

        result = WebUtils.httpRequest("GET", requestUrl, authorization);
        apiResponse = mapper.readValue(result, Response.class);

        return apiResponse;
    }


}
