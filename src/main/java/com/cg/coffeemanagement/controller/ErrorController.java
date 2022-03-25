package com.cg.coffeemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/errors")
public class ErrorController {

    @GetMapping
    public ModelAndView showErrorPage() {
        return new ModelAndView("404");
    }
}
