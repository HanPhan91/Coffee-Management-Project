package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.model.Cart;
import com.cg.coffeemanagement.model.Drink;
import com.cg.coffeemanagement.model.dto.CartItemDto;
import com.cg.coffeemanagement.model.dto.DrinkDto;
import com.cg.coffeemanagement.services.Cart.CartService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartApi {

    @Autowired
    CartService cartService;

    @Autowired
    CoffeeTableService coffeeTableService;



    @Autowired
    AppUtil appUtil;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> showCart(@PathVariable Long id){
        Cart cart = cartService.getByTableId(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }



}
