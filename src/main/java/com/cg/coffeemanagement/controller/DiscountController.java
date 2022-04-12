package com.cg.coffeemanagement.controller;


import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.CatalogsMaterial;
import com.cg.coffeemanagement.model.Discount;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.DiscountDTO;
import com.cg.coffeemanagement.services.Users.IUserService;
import com.cg.coffeemanagement.services.discount.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private IDiscountService discountService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ModelAndView showDiscount(){
        ModelAndView modelAndView = new ModelAndView();
        List<Discount> discounts = discountService.findByDeletedFalse();
        List<DiscountDTO> discountDTOs = discounts.stream().map(DiscountDTO::parse).collect(Collectors.toList());
        User user = userService.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("discount/list");
        modelAndView.addObject("discounts", discountDTOs);
        return modelAndView;
    }

    @GetMapping("/deleted")
    public ModelAndView showDiscountDeleted(){
        ModelAndView modelAndView = new ModelAndView();
        List<Discount> discounts = discountService.findByDeletedTrue();
        User user = userService.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("discount/deleted");
        modelAndView.addObject("discounts", discounts);
        return modelAndView;
    }
}
