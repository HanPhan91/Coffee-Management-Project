package com.cg.coffeemanagement.services.Order;


import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.repository.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository cartRepository;

    @Override
    public List<Order> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public Order save(Order cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Optional<Order> getByCoffeeTableId(Long id) {
        return cartRepository.getByCoffeeTableId(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        cartRepository.deleteOrderById(id);
    }
}
