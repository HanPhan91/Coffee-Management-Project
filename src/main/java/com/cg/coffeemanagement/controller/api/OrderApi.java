package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.model.BillDetail;
import com.cg.coffeemanagement.model.Order;
import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
import com.cg.coffeemanagement.repository.Bill.BillRepository;
import com.cg.coffeemanagement.repository.BillDetail.BillDetailRepository;
import com.cg.coffeemanagement.repository.OrderItem.OrderItemRepository;
import com.cg.coffeemanagement.services.Bill.BillService;
import com.cg.coffeemanagement.services.BillDetailService.BillDetailService;
import com.cg.coffeemanagement.services.Order.OrderService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.services.OrderItem.OrderItemService;
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
    public ResponseEntity<Order> showCart(@PathVariable Long id){
        Order order = orderService.getByCoffeeTableId(id).get();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/create/{idtable}")
    public ResponseEntity<?> doCreate(@PathVariable Long idtable, @RequestBody List<OrderItemDto> listOrders){
        Optional<Order> opOrder = orderService.getByCoffeeTableId(idtable);
        BigDecimal subPrice = BigDecimal.valueOf(0);
        if (opOrder.isPresent()){
            Order order = opOrder.get();
            for (OrderItemDto orderItemDto : listOrders){
                OrderItem orderItem = orderItemDto.toOderItem();
                orderItem.setOrder(order);
                orderItemService.save(orderItem);
                subPrice.add(orderItemDto.getTotalPrice());
            }
            order.setSubAmount(subPrice);
            BigDecimal totalAmount = subPrice;
            order.setTotalAmount(subPrice);
            orderService.save(order);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/delete/{idtable}")
    public ResponseEntity<?> doDelete(@PathVariable Long idtable){
        Optional<Order> opOrder = orderService.getByCoffeeTableId(idtable);
        if (opOrder.isPresent()){
            Order order = opOrder.get();
            Bill bill = new Bill();
            bill.setTable(order.getTable());
            bill.setSubAmount(order.getSubAmount());
            bill.setTotalAmount(order.getTotalAmount());
            billService.save(bill);
            for (OrderItem orderItem : order.getOrderItem()){
                BillDetail billDetail = orderItem.toBillDetail();
                billDetail.setBill(bill);
                billDetailService.save(billDetail);
            }
            orderItemService.deleteAllByOrder(order);
            Order newOrder = order.newOrder();
            orderService.deleteOrderById(order.getId());
            orderService.save(newOrder);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
