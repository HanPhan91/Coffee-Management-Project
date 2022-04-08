package com.cg.coffeemanagement.controller;


import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.CoffeeTable;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.services.Users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/tables")
public class CoffeeTableController {

    @Autowired
    private CoffeeTableService coffeeTableService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ModelAndView showListTable() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coffeeTable/list");
        List<CoffeeTable> table = coffeeTableService.findAllNotDeleted();
        User user = userService.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.addObject("tables", table);
        return modelAndView;
    }


}

