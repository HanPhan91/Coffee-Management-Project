package com.cg.coffeemanagement.controller;


import com.cg.coffeemanagement.model.CoffeeTable;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
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
        CoffeeTableService coffeeTableService;


    @GetMapping
    public ModelAndView showListTable(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coffeeTable/list");
        List<CoffeeTable> table = coffeeTableService.findAllNotDeleted();
        modelAndView.addObject("tables", table);
        return modelAndView;
    }


}

