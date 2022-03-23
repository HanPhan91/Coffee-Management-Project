package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.model.Cart;
import com.cg.coffeemanagement.model.CoffeeTable;
import com.cg.coffeemanagement.model.Drink;
import com.cg.coffeemanagement.services.Cart.CartService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    CoffeeTableService coffeeTableService;

    @Autowired
    DrinkService drinkService;

    @GetMapping
    public ModelAndView listCart(){
        ModelAndView modelAndView = new ModelAndView();
//        List<Cart> carts = cartService.findAll();
        modelAndView.setViewName("cart/list");
        List<Drink> drinks = drinkService.findAllNotDeleted();
        List<CoffeeTable> tables = coffeeTableService.findAllNotDeleted();
        modelAndView.addObject("tables", tables);
        modelAndView.addObject("drinks", drinks);
        return modelAndView;
    }




}
