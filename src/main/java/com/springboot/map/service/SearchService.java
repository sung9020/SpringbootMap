package com.springboot.map.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.map.dto.ApiInfoDto;
import com.springboot.map.dto.KeywordRankDto;
import com.springboot.map.dto.ResultDto;
import com.springboot.map.dto.RequestDto;
import com.springboot.map.dto.ResponseDto;
import com.springboot.map.entity.KeywordRank;
import com.springboot.map.repositoy.KeywordRankRepository;
import com.springboot.map.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    KakaoService kakaoService;

    @Autowired
    KeywordRankRepository keywordRankRepository;

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

    public ResponseDto getKakaoData(RequestDto requestDto) throws Exception{

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
    /* git flow test */
    @Transactional(readOnly = true)
    public List<KeywordRankDto> getTopKeywordRank(){

       List<KeywordRankDto> result = keywordRankRepository.findTop10ByOrderByTotalcountDesc()
               .sorted( Comparator.comparing(KeywordRank::getKeyword))
               .sorted( Comparator.comparingInt( (KeywordRank k)  -> k.getTotalcount()).reversed())
               .map(KeywordRankDto::new).collect(Collectors.toList());


       return result;
    }

    @Transactional
    public ResultDto saveKeywordRank(RequestDto requestDto){
        ResultDto result = new ResultDto();
        KeywordRankDto KeywordRankDto = new KeywordRankDto();;
        if(keywordRankRepository.findByKeyword(requestDto.getQuery()).findFirst().isPresent()){
            KeywordRankDto = keywordRankRepository.findByKeyword(requestDto.getQuery()).findFirst().map(KeywordRankDto::new).get();
            KeywordRankDto.setTotalcount(KeywordRankDto.getTotalcount() + 1);

        }else{
            KeywordRankDto.setId(0);
            KeywordRankDto.setKeyword(requestDto.getQuery());
            KeywordRankDto.setTotalcount(1);

        }

        KeywordRank saveData = KeywordRankDto.getEntity(KeywordRankDto.getId(),KeywordRankDto.getKeyword(),KeywordRankDto.getTotalcount());
        if(keywordRankRepository.save(saveData).getId() > 0){
            result.setResult(true);
            result.setMsg("입력 성공");
        }else{
            result.setResult(false);
            result.setMsg("입력 실패");
        }
        return result;

    }


}
