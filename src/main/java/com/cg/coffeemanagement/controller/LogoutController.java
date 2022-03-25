package com.cg.coffeemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    public ModelAndView doLogout(){

        return new ModelAndView("redirect: /");
    }
}
