package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.*;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
import com.cg.coffeemanagement.repository.Bill.BillRepository;
import com.cg.coffeemanagement.repository.BillDetail.BillDetailRepository;
import com.cg.coffeemanagement.repository.OrderItem.OrderItemRepository;
import com.cg.coffeemanagement.services.Bill.BillService;
import com.cg.coffeemanagement.services.BillDetailService.BillDetailService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import com.cg.coffeemanagement.services.Order.OrderService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.services.OrderItem.OrderItemService;
import com.cg.coffeemanagement.services.discount.IDiscountService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

    @Autowired
    IDiscountService discountService;

    @Autowired
    DrinkService drinkService;

    @Autowired
    OrderService orderService;

    @Autowired
    CoffeeTableService coffeeTableService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    BillService billService;

    @Autowired
    BillDetailService billDetailService;

    @Autowired
    AppUtil appUtil;

    @GetMapping("/{id}")
    public ResponseEntity<Order> showCart(@PathVariable Long id) {
        Order order = orderService.getByCoffeeTableId(id).get();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/create/{idtable}")
    public ResponseEntity<?> doCreate(@PathVariable Long idtable, @RequestBody List<OrderItemDto> listOrders, @RequestParam String discount) {
        int percentDiscount;
        Discount activeDiscount = null;
        if (discount.isEmpty()) {
            percentDiscount = 0;
        } else {
            Optional<Discount> optionalDiscount = discountService.findDiscountByCodeAndDeletedFalseAndQuantityIsGreaterThanAndEndedAtGreaterThanEqual(discount);
            if (optionalDiscount.isPresent()) {
                activeDiscount = optionalDiscount.get();
                percentDiscount = activeDiscount.getPercentDiscount();
            } else {
                throw new DataInputException("Voucher không tồn tại hoặc đã hết hạn sử dụng");
            }
        }
        Optional<Order> opOrder = orderService.getByCoffeeTableId(idtable);
        BigDecimal subPrice = BigDecimal.valueOf(0);
        if (opOrder.isPresent()) {
            Order order = opOrder.get();
            for (OrderItemDto orderItemDto : listOrders) {
                OrderItem orderItem = orderItemDto.toOderItem();
                Drink drink = drinkService.findById(orderItemDto.getDrink()).get();
                orderItem.setDrink(drink);
                BigDecimal totalPrice = drink.getPrice().multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));
                orderItem.setTotalPrice(totalPrice);
                orderItem.setOrder(order);
                orderItemService.save(orderItem);
                subPrice = subPrice.add(totalPrice);
            }
            order.setSubAmount(subPrice);
            order.setDiscount(activeDiscount);
            double valueDiscount = (double) percentDiscount / 100;
            BigDecimal totalAmount = subPrice.subtract(subPrice.multiply(BigDecimal.valueOf(valueDiscount)));
            order.setTotalAmount(totalAmount);
            orderService.save(order);
            if (activeDiscount != null) {
                discountService.reduceQuantity(activeDiscount);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/pay/{idtable}")
    public ResponseEntity<?> doPay(@PathVariable Long idtable) {
        Optional<Order> opOrder = orderService.getByCoffeeTableId(idtable);
        if (opOrder.isPresent()) {
            Order order = opOrder.get();
            Bill bill = new Bill();
            bill.setTable(order.getCoffeeTable());
            bill.setSubAmount(order.getSubAmount());
            bill.setDiscount(order.getDiscount());
            bill.setTotalAmount(order.getTotalAmount());
            billService.save(bill);
            List<OrderItem> listOrderItems = orderItemService.findAllByOrder(order);
            for (OrderItem orderItem : listOrderItems) {
                BillDetail billDetail = orderItem.toBillDetail();
                billDetail.setBill(bill);
                billDetailService.save(billDetail);
            }
            orderItemService.deleteAllByOrder(order);
            Order newOrder = order.newOrder();
            orderService.deleteOrderById(order.getId());
            orderService.save(newOrder);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
