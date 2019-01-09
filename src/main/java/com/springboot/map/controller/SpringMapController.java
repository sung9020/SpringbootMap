package com.springboot.map.controller;

import com.springboot.map.dto.RequestDto;
import com.springboot.map.dto.ResponseDto;
import com.springboot.map.service.SearchService;
import com.springboot.map.service.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins = "*")
public class SpringMapController {

    @Autowired
    SearchService searchService;

    @Autowired
    SpringSecurityService springSecurityService;

    @RequestMapping(value = "/main")
    public String main(Model model) throws Exception{

        return "main";
    }

    @RequestMapping(value = "/login")
    public String login(Model model) throws Exception{

        return "login";
    }

    @RequestMapping( value = "/search", method= RequestMethod.POST)
    public ResponseDto search(@RequestBody RequestDto userRequestDto) throws Exception{

        ResponseDto responseDto = searchService.getKakaoKeywordData(userRequestDto);

        return responseDto;
    }

}
