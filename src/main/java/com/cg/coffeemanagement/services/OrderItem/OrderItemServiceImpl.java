package com.cg.coffeemanagement.services.OrderItem;

import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
<<<<<<< HEAD
=======
import com.cg.coffeemanagement.model.dto.OrderItemMenuDto;
>>>>>>> c7015b7bac490e679998c2e3ef926c60c0b325ce
import com.cg.coffeemanagement.repository.OrderItem.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository cartItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public OrderItem getById(Long id) {
        return cartItemRepository.getById(id);
    }

    @Override
    public OrderItem save(OrderItem cartItem) {
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
    public List<OrderItem> findAllNotDeleted() {
        return null;
    }

    @Override
    public List<OrderItem> findAllDeleted() {
        return null;
    }

    @Override
    public void deletedCartItemByCatalog(Long idCatalog) {

    }

    @Override
    public void restoreCartItemByCatalog(Long idCatalog) {

    }

    @Override
    public void deleteAllByOrder(Order order) {
        cartItemRepository.deleteAllByOrder(order);
    }

    @Override
    public List<OrderItem> findAllByOrder(Order order) {
        return cartItemRepository.findAllByOrder(order);
    }

//    @Override
//    public List<OrderItemMenuDto> findAllOrderItemMenuDTOByOrder(String idOrder) {
//        return cartItemRepository.findAllOrderItemMenuDTOByOrder(idOrder);
//    }

    @Override
    public List<OrderItemMenuDto> findAllOrderItemMenuDTOByOrder(Order objOrder) {
        return cartItemRepository.findAllOrderItemMenuDTOByOrder(objOrder);
    }

    @Override
    public List<OrderItemDto> findAllOrderItemDTOByOrder(String idOrder) {
        return cartItemRepository.findAllOrderItemDTOByOrder(idOrder);
    }

    @Override
    public BigDecimal calcSubAmount(Long id) {
        return cartItemRepository.calcSubAmount(id);
    }
}
