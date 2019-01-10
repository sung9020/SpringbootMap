package com.springboot.map.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.map.dto.KeywordRankDto;
import com.springboot.map.dto.RequestDto;
import com.springboot.map.dto.ResponseDto;
import com.springboot.map.dto.ResultDto;
import com.springboot.map.service.SearchService;
import com.springboot.map.service.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.MalformedObjectNameException;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class SpringMapController {

    @Autowired
    SearchService searchService;

    @Autowired
    SpringSecurityService springSecurityService;

    @RequestMapping(value = "/main")
    public String main(Model model) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
       List<KeywordRankDto> keywordRankDtoList = searchService.getTopKeywordRank();
       model.addAttribute("keywordRankDtoList", keywordRankDtoList);

        return "main";
    }

    @RequestMapping(value = "/login")
    public String login(Model model) throws Exception{

        return "login";
    }

    @RequestMapping( value = "/search", method= RequestMethod.POST)
    public @ResponseBody ResponseDto search(@RequestBody RequestDto userRequestDto) throws Exception{

        ResultDto keywordRank = searchService.saveKeywordRank(userRequestDto);
        ResponseDto responseDto = searchService.getKakaoData(userRequestDto);

        return responseDto;
    }

    @RequestMapping( value = "/keywordrank", method= RequestMethod.POST)
    public @ResponseBody List<KeywordRankDto> keywordrank() throws Exception{

        List<KeywordRankDto> keywordRankDtoList = searchService.getTopKeywordRank();

        return keywordRankDtoList;
    }

}
