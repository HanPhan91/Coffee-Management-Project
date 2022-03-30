package com.cg.coffeemanagement.services.CartItem;


import com.cg.coffeemanagement.model.CartItem;
import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemService extends IGeneralServices<CartItem> {

    @Override
    List<CartItem> findAll();

    @Override
    Optional<CartItem> findById(Long id);

    @Override
    CartItem getById(Long id);

    @Override
    CartItem save(CartItem cartItem);

    @Override
    void remove(Long id);

    void deleteCartItem(@Param("id") Long id);

    void restoreCartItem(@Param("id") Long id);

    List<CartItem> findAllNotDeleted();

    List<CartItem> findAllDeleted();

    void deletedCartItemByCatalog(@Param("idCatalog") Long idCatalog);

    void restoreCartItemByCatalog(@Param("idCatalog") Long idCatalog);
}
