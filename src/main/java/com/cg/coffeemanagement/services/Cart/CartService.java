package com.cg.coffeemanagement.services.Cart;

import com.cg.coffeemanagement.model.Cart;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartService extends IGeneralServices<Cart> {

    @Override
    List<Cart> findAll();

    @Override
    Optional<Cart> findById(Long id);

    @Override
    Cart getById(Long id);

    @Override
    Cart save(Cart cart);

    @Override
    void remove(Long id);

    void deleteCartById(@Param("id") Long id);

//    void restoreCart(@Param("id") Long id);
}
