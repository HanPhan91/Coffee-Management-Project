package com.cg.coffeemanagement.services.CartItem;

import com.cg.coffeemanagement.model.CartItem;
import com.cg.coffeemanagement.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CartItemServiceImpl implements CartItemService{

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public CartItem getById(Long id) {
        return cartItemRepository.getById(id);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void restoreCartItem(Long id) {

    }

    @Override
    public List<CartItem> findAllNotDeleted() {
        return null;
    }

    @Override
    public List<CartItem> findAllDeleted() {
        return null;
    }

    @Override
    public void deletedCartItemByCatalog(Long idCatalog) {

    }

    @Override
    public void restoreCartItemByCatalog(Long idCatalog) {

    }
}
