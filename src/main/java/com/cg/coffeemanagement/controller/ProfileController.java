package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Users.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private IUserServices userServices;

    @GetMapping("/{username}")
    public ModelAndView getProfile(@PathVariable String username){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile/profile");
        User userLogin = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", userLogin);
        Optional<User> userOp = userServices.getByUsername(username);
        if (userOp.isPresent()){
            User editUser = userOp.get();
            if (editUser.getUsername().equals(userLogin.getUsername())){
                modelAndView.addObject("editUser",editUser);
                return modelAndView;
            }
            else {
                return new ModelAndView("redirect:/errors");
            }
        }
        else {
            return new ModelAndView("redirect:/errors");
        }
    }
}
