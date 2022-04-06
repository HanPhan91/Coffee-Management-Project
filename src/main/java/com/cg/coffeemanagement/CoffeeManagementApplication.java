package com.cg.coffeemanagement;
import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.services.Order.OrderService;
import com.cg.coffeemanagement.services.OrderItem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class CoffeeManagementApplication implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(CoffeeManagementApplication.class, args);
    }

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Override
    public void run(String... args) throws Exception {
        Long id = Long.valueOf(1649217878);
        Order order = orderService.getById(id);

        System.out.println(orderItemService.calcSubAmount(id));
    }
}
