package com.example.bookmanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthenticationController {

    @GetMapping(value = "/")
    public ModelAndView root(){
        return new ModelAndView("redirect:/index");
    }

}
