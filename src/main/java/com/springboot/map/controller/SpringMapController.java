package com.springboot.map.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.map.dto.KeywordRankDto;
import com.springboot.map.dto.RequestDto;
import com.springboot.map.dto.ResponseDto;
import com.springboot.map.dto.ResultDto;
import com.springboot.map.service.SearchService;
import com.springboot.map.service.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class SpringMapController {

    @Autowired
    SearchService searchService;

    @Autowired
    SpringSecurityService springSecurityService;

    /* main */
    @RequestMapping(value = "/")
    public String index() throws Exception{

        return "redirect:/main";
    }

    /* main */
    @RequestMapping(value = "/main")
    public String main(Model model) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
       List<KeywordRankDto> keywordRankDtoList = searchService.getTopKeywordRank();
       model.addAttribute("keywordRankDtoList", keywordRankDtoList);

        return "main";
    }

    /* login */
    @RequestMapping(value = "/login")
    public String login(Model model, HttpServletRequest request) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String error = request.getParameter("error");
        if(auth.getPrincipal().equals("anonymousUser") || auth == null){
            if(!StringUtils.isEmpty(error)){
                model.addAttribute("error", error);
            }
            return "login";
        }else{
            return "redirect:/main";
        }
    }

    /* search Page */
    @RequestMapping( value = "/search", method= RequestMethod.POST)
    public @ResponseBody ResponseDto search(@RequestBody RequestDto userRequestDto) throws Exception{

        ResultDto keywordRank = searchService.saveKeywordRank(userRequestDto);
        ResponseDto responseDto = searchService.getKakaoData(userRequestDto);

        return responseDto;
    }

    /* search top 10 */
    @RequestMapping( value = "/keywordrank", method= RequestMethod.POST)
    public @ResponseBody List<KeywordRankDto> keywordrank() throws Exception{

        List<KeywordRankDto> keywordRankDtoList = searchService.getTopKeywordRank();

        return keywordRankDtoList;
    }

    /* search next Page */
    @RequestMapping( value = "/page", method= RequestMethod.POST)
    public @ResponseBody ResponseDto Page(@RequestBody RequestDto userRequestDto) throws Exception{

        ResponseDto responseDto = searchService.getKakaoData(userRequestDto);

        return responseDto;
    }
}
