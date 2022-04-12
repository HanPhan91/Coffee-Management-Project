package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Users.IUserService;
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
    private IUserService userServices;

    @GetMapping
    public ModelAndView showUser(){
        ModelAndView modelAndView= new ModelAndView();
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.setViewName("users/list");
        List<User> users = userServices.findUserNotDeletedAndPermissionSmaller(user.getStaff().getPosition().getPermission().getPermissionAccess());
        modelAndView.addObject("user", user);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/deleted")
    public ModelAndView showUserDeleted(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("users/deleted");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        List<User> users = userServices.findUserNotDeletedAndPermissionSmaller(user.getStaff().getPosition().getPermission().getPermissionAccess());
        modelAndView.addObject("user", user);
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
