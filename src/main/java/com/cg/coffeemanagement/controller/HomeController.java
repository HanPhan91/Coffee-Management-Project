package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Users.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IUserServices userServices;

    @GetMapping()
    public ModelAndView showHome(){
        ModelAndView modelAndView= new ModelAndView();
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.setViewName("home");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
