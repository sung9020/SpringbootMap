package com.springboot.map.controller;

import com.springboot.map.entity.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SpringMapController {

    @RequestMapping("/main")
    public Response main(HttpServletRequest request) throws Exception{

        Response response = new Response();

        return response;
    }

    @RequestMapping("/search")
    public Response search(HttpServletRequest request) throws Exception{

        Response response = new Response();

        return response;
    }

}
