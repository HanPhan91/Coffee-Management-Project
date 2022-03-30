package com.cg.coffeemanagement.repository;


import com.cg.coffeemanagement.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {


}
