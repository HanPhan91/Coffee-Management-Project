package com.cg.coffeemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/temp")
public class TempController {

    @GetMapping
    public ModelAndView showHome(){
        return new ModelAndView("temp/upload");
    }
}
