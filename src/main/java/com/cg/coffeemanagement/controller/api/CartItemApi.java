package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.model.CartItem;
import com.cg.coffeemanagement.model.dto.CartItemDto;
import com.cg.coffeemanagement.services.Cart.CartService;
import com.cg.coffeemanagement.services.CartItem.CartItemService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CartItemApi {
    @Autowired
    CartService cartService;

    @Autowired
    CartItemDto cartItemDto;

    @Autowired
    AppUtil appUtil;

    @Autowired
    CoffeeTableService coffeeTableService;

    @Autowired
    DrinkService drinkService;

    @Autowired
    CartItemService cartItemService;

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@PathVariable Long id,@Validated @RequestBody CartItemDto cartItemDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        CartItem cartItem = new CartItem();

        cartItem.setCart(cartService.getById(cartItemDto.getCart()));
        cartItem.setDrink(drinkService.getById(cartItemDto.getDrink()));
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setTotalPrice(cartItemDto.getTotalPrice());
        CartItem returnCartItem = cartItemService.save(cartItem);
        return new ResponseEntity<>(returnCartItem, HttpStatus.OK);
    }
}
