package com.cg.coffeemanagement.services.Order;


import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.repository.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public Order save(Order cart) {
        return orderRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public void deleteCartById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> getByCoffeeTableId(Long id) {
        return orderRepository.getByCoffeeTableId(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteOrderById(id);
    }


}
