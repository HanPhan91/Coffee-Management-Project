package com.cg.coffeemanagement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class CoffeeManagementApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CoffeeManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BigDecimal subAmount = BigDecimal.valueOf(100000);
        int discountAccept = 10;
        double discount = (double) discountAccept / 100;
        System.out.println(subAmount.multiply(BigDecimal.valueOf(discount)));
        BigDecimal totalAmount = subAmount.subtract(subAmount.multiply(BigDecimal.valueOf(discount)));
        System.out.println(totalAmount);
    }
}
