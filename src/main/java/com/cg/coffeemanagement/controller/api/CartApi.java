package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.model.Cart;
import com.cg.coffeemanagement.services.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartApi {

    @Autowired
    CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> showCart(@PathVariable Long id){
        Cart cart = cartService.getByTableId(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


}
