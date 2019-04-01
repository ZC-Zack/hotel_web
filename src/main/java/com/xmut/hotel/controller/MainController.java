package com.xmut.hotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backstage")
public class MainController {
    @RequestMapping("/main")
    public String demo() {
        return "backstage/main";
    }
}
