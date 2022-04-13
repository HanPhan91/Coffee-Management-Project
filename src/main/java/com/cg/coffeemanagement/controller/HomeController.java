package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Bill.BillService;
import com.cg.coffeemanagement.services.Users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IUserService userServices;

    @Autowired
    private BillService billService;

    @GetMapping()
    public ModelAndView showHome(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("home");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        String incomeToday = billService.incomeToday();
        String incomeToMonth = billService.incomeToMonth();
        String billToday = billService.billToday();
        modelAndView.addObject("incomeToday", incomeToday);
        modelAndView.addObject("incomeToMonth", incomeToMonth);
        modelAndView.addObject("billToday", billToday);
        return modelAndView;
    }

}
