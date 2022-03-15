package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Users.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserServices userServices;

    @GetMapping
    public ModelAndView showHome(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("users/list");
        List<User> users = userServices.findAll();
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
