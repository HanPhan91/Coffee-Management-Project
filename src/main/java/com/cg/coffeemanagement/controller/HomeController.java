package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.SummaryDrink;
import com.cg.coffeemanagement.services.Bill.BillService;
import com.cg.coffeemanagement.services.BillDetailService.BillDetailService;
import com.cg.coffeemanagement.services.Staffs.IStaffServices;
import com.cg.coffeemanagement.services.Users.IUserService;
import com.cg.coffeemanagement.services.discount.IDiscountService;
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

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private IStaffServices staffServices;
    @Autowired
    private IDiscountService discountService;

    @GetMapping()
    public ModelAndView showHome(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("home");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        BigDecimal incomeToday = billService.incomeToday();
        BigDecimal incomeToMonth = billService.incomeToMonth();
        BigDecimal billToday = billService.billToday();
        List<SummaryDrink> listSummary = billDetailService.summaryDrinkInBillDetail();
        int sumStaff = staffServices.countStaff();
        int sumVoucher = discountService.countByDeletedFalseAndQuantityIsGreaterThanAndEndedAtGreaterThanEqual();
        modelAndView.addObject("incomeToday", incomeToday);
        modelAndView.addObject("incomeToMonth", incomeToMonth);
        modelAndView.addObject("billToday", billToday);
        modelAndView.addObject("listSummary", listSummary);
        modelAndView.addObject("sumStaff", sumStaff);
        modelAndView.addObject("sumVoucher", sumVoucher);
        return modelAndView;
    }
}
