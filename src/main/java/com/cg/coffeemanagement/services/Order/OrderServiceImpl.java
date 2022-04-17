package com.cg.coffeemanagement.services.Order;


import com.cg.coffeemanagement.model.*;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
import com.cg.coffeemanagement.repository.Order.OrderRepository;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import com.cg.coffeemanagement.services.OrderItem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    CoffeeTableService coffeeTableService;

    @Autowired
    DrinkService drinkService;

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
    public Optional<Order> getByCoffeeTable(Long id) {
        return orderRepository.getByCoffeeTable(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteOrderById(id);
    }

    @Override
    public void doSplitOrder(Order oldOrder, Order newOrder, List<OrderItemDto> listOrderSplit, Discount discount, User user) {
        List<OrderItem> listOldOrder = orderItemService.findAllByOrder(oldOrder);
        double percentDiscount = 0;
        if (discount != null) {
            percentDiscount = (double) discount.getPercentDiscount() / 100;
        }
        //xử lý order cũ khi tách thức uống
        for (OrderItemDto orderItemDto : listOrderSplit) {
            for (OrderItem orderItem : listOldOrder) {
                if (orderItem.getDrink().getId().compareTo(orderItemDto.getId()) == 0) {
                    int quantity = orderItem.getQuantity() - orderItemDto.getQuantity();
                    if (quantity == 0) {
                        listOldOrder.remove(orderItem);
                    } else {
                        orderItem.setQuantity(quantity);
                    }
                    break;
                }
            }
        }

        orderItemService.deleteAllByOrder(oldOrder);

        if (listOldOrder.isEmpty()) {
            CoffeeTable oldTable = coffeeTableService.findById(oldOrder.getId()).get();
            oldTable.setUsed(false);
            coffeeTableService.save(oldTable);
            oldOrder.setDiscount(null);
            oldOrder.setStaffName(null);
            oldOrder.setTotalAmount(null);
            oldOrder.setSubAmount(null);
            save(oldOrder);
        } else {
            for (OrderItem orderItem : listOldOrder) {
                orderItem.setOrder(oldOrder);
                orderItemService.save(orderItem);
            }
            BigDecimal subAmountOldOrder = orderItemService.calcSubAmount(oldOrder.getId());
            oldOrder.setSubAmount(subAmountOldOrder);
            BigDecimal totalAmount = subAmountOldOrder.subtract(subAmountOldOrder.multiply(BigDecimal.valueOf(percentDiscount)));
            oldOrder.setTotalAmount(totalAmount);
            save(oldOrder);
            CoffeeTable oldTable = coffeeTableService.findById(oldOrder.getId()).get();
            oldTable.setUsed(true);
            coffeeTableService.save(oldTable);
        }
        // xử lý xong

        //xử lý order mới
        for (OrderItemDto orderItemDto : listOrderSplit) {
            OrderItem orderItem = orderItemDto.toOderItem();
            Drink drink = drinkService.findById(orderItem.getId()).get();
            orderItem.setDrink(drink);
            orderItem.setPrice(drink.getPrice());
            orderItem.setQuantity(orderItemDto.getQuantity());
            BigDecimal totalPrice = drink.getPrice().multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));
            orderItem.setTotalPrice(totalPrice);
            orderItem.setOrder(newOrder);
            orderItemService.save(orderItem);
        }
        BigDecimal subAmountNewOrder = orderItemService.calcSubAmount(newOrder.getId());
        newOrder.setSubAmount(subAmountNewOrder);
        BigDecimal totalAmountNewOrder = subAmountNewOrder.subtract(subAmountNewOrder.multiply(BigDecimal.valueOf(percentDiscount)));
        newOrder.setTotalAmount(totalAmountNewOrder);
        newOrder.setStaffName(user.getStaff().getName());
        newOrder.setDiscount(discount);
        save(newOrder);
        CoffeeTable newTable = coffeeTableService.findById(newOrder.getId()).get();
        newTable.setUsed(true);
        coffeeTableService.save(newTable);
    }
}
