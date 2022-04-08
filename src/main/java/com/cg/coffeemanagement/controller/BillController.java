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

import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private IUserService userServices;

    @GetMapping
    public ModelAndView showBill(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bill/bill");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        List<Bill> billList = billService.findAll();
        modelAndView.addObject("billList", billList);
        return modelAndView;
    }
}
