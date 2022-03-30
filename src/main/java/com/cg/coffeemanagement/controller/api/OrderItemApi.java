package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
import com.cg.coffeemanagement.services.Order.OrderService;
import com.cg.coffeemanagement.services.OrderItem.OrderItemService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OrderItemApi {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemDto orderItemDto;

    @Autowired
    AppUtil appUtil;

    @Autowired
    CoffeeTableService coffeeTableService;

    @Autowired
    DrinkService drinkService;

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody OrderItemDto orderItemDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        OrderItem orderItem = new OrderItem();

        orderItem.setCart(orderService.getById(orderItemDto.getCart()));
        orderItem.setDrink(drinkService.getById(orderItemDto.getDrink()));
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setTotalPrice(orderItemDto.getTotalPrice());
        OrderItem returnCartItem = orderItemService.save(orderItem);
        return new ResponseEntity<>(returnCartItem, HttpStatus.OK);
    }
}
