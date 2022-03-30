package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.services.Order.OrderService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class OrderApi {

    @Autowired
    OrderService orderService;

    @Autowired
    CoffeeTableService coffeeTableService;



    @Autowired
    AppUtil appUtil;

    @GetMapping("/{id}")
    public ResponseEntity<Order> showCart(@PathVariable Long id){
        Order order = orderService.getByTableId(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }



}
