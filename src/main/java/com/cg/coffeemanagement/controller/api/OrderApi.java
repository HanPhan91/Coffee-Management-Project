package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.*;
import com.cg.coffeemanagement.model.dto.OrderItemDto;
import com.cg.coffeemanagement.model.dto.OrderItemMenuDto;
import com.cg.coffeemanagement.services.Bill.BillService;
import com.cg.coffeemanagement.services.BillDetailService.BillDetailService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import com.cg.coffeemanagement.services.Order.OrderService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.services.OrderItem.OrderItemService;
import com.cg.coffeemanagement.services.Users.IUserService;
import com.cg.coffeemanagement.services.discount.IDiscountService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

    @Autowired
    private IDiscountService discountService;

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CoffeeTableService coffeeTableService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IUserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<?> showOrderByIdTable(@PathVariable Long id) {
        Order order = orderService.getByCoffeeTable(id).get();
        List<OrderItemMenuDto> orderItemMenuDto = orderItemService.findAllOrderItemMenuDTOByOrder(order);
        String discount = order.getDiscount() != null ? order.getDiscount().getCode() : null;
        for (OrderItemMenuDto orderItem : orderItemMenuDto){
            orderItem.setDiscount(discount);
        }
        return new ResponseEntity<>(orderItemMenuDto, HttpStatus.OK);
    }


    @PostMapping("/create/{idtable}")
    public ResponseEntity<?> doCreate(@PathVariable Long idtable, @RequestBody List<OrderItemMenuDto> listMenuOrders, @RequestParam String discount) {
        User user = userService.getByUsername(Principal.getPrincipal()).get();
        int percentDiscount;
        Discount activeDiscount = null;
        if (discount.isEmpty()) {
            percentDiscount = 0;
        }
        else {
            Optional<Discount> optionalDiscount = discountService.findDiscountByCodeAndDeletedFalseAndQuantityIsGreaterThanAndEndedAtGreaterThanEqual(discount);
            if (optionalDiscount.isPresent()) {
                activeDiscount = optionalDiscount.get();
                percentDiscount = activeDiscount.getPercentDiscount();
            } else {
                throw new DataInputException("Voucher không thể sử dụng");
            }
        }
        Optional<Order> opOrder = orderService.getByCoffeeTable(idtable);
        if (opOrder.isPresent()) {
            Order order = opOrder.get();
            List<OrderItemDto> orderItemDtoList = new ArrayList<>();
            for (OrderItemMenuDto orderItemMenuDto : listMenuOrders) {
                Drink drink = drinkService.findById(orderItemMenuDto.getId()).get();
                orderItemDtoList.add(orderItemMenuDto.toOrderItemDto(drink));
            }
            orderItemService.deleteAllByOrder(order);

            for (OrderItemDto orderItemDto : orderItemDtoList) {
                OrderItem orderItem = orderItemDto.toOderItem();
                Drink drink = drinkService.findById(orderItemDto.getId()).get();
                orderItem.setDrink(drink);
                orderItem.setPrice(drink.getPrice());
                BigDecimal totalPrice = drink.getPrice().multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));
                orderItem.setTotalPrice(totalPrice);
                orderItem.setOrder(order);
                orderItemService.save(orderItem);
            }

            BigDecimal subPrice = orderItemService.calcSubAmount(order.getId());
            order.setSubAmount(subPrice);
            order.setDiscount(activeDiscount);
            double valueDiscount = (double) percentDiscount / 100;
            BigDecimal totalAmount = subPrice.subtract(subPrice.multiply(BigDecimal.valueOf(valueDiscount)));
            order.setTotalAmount(totalAmount);
            order.setStaffName(user.getStaff().getName());
            orderService.save(order);
            if (activeDiscount != null) {
                discountService.reduceQuantity(activeDiscount);
            }
            CoffeeTable coffeeTable = coffeeTableService.findById(idtable).get();
            coffeeTable.setUsed(true);
            coffeeTableService.save(coffeeTable);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/pay/{idtable}")
    public ResponseEntity<?> doPay(@PathVariable Long idtable) {
        User user = userService.getByUsername(Principal.getPrincipal()).get();
        Optional<Order> opOrder = orderService.getByCoffeeTable(idtable);
        if (opOrder.isPresent()) {
            Order order = opOrder.get();
            Bill bill = new Bill();
            CoffeeTable coffeeTable = coffeeTableService.findById(idtable).get();
            bill.setCoffeeTable(coffeeTable.getName());
            bill.setSubAmount(order.getSubAmount());
            if (order.getDiscount() != null) {
                bill.setCodeDiscount(order.getDiscount().getCode());
                bill.setDiscountPercent(String.valueOf(order.getDiscount().getPercentDiscount()));
            } else {
                bill.setCodeDiscount(null);
                bill.setDiscountPercent(null);
            }
            bill.setTotalAmount(order.getTotalAmount());
            bill.setStaffName(user.getStaff().getName());
            billService.save(bill);
            List<OrderItem> listOrderItems = orderItemService.findAllByOrder(order);
            for (OrderItem orderItem : listOrderItems) {
                BillDetail billDetail = orderItem.toBillDetail();
                billDetail.setBill(bill);
                billDetailService.save(billDetail);
            }
            orderItemService.deleteAllByOrder(order);
            order.setSubAmount(null);
            order.setTotalAmount(null);
            order.setDiscount(null);
            order.setStaffName(null);
            orderService.save(order);
            coffeeTable.setUsed(false);
            coffeeTableService.save(coffeeTable);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/split/{oldTable}/{newTable}")
    public ResponseEntity<?> splitTable(@PathVariable Long oldTable, @PathVariable Long newTable, @RequestBody List<OrderItemDto> listOrders) {
        User user = userService.getByUsername(Principal.getPrincipal()).get();
        Optional<Order> opOldOrder = orderService.getByCoffeeTable(oldTable);
        if (opOldOrder.isPresent()) {
            Optional<Order> opNewOrder = orderService.getByCoffeeTable(newTable);
            if (opNewOrder.isPresent()) {
                Order oldOrder = opOldOrder.get();
                Order newOrder = opNewOrder.get();
                Discount discount = null;
                if (oldOrder.getDiscount() != null) {
                    discount = oldOrder.getDiscount();
                }
                orderService.doSplitOrder(oldOrder, newOrder, listOrders, discount, user);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
