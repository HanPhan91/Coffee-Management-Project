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
        Optional<Order> order = orderService.getByCoffeeTableId(id);
//        List<OrderItem> orderItems = orderItemService.findAllByOrder(order.get());

        List<OrderItemMenuDto> orderItemMenuDto = orderItemService.findAllOrderItemMenuDTOByOrder(order.get());

        return new ResponseEntity<>(orderItemMenuDto, HttpStatus.OK);
    }


    @PostMapping("/create/{idtable}")
    public ResponseEntity<?> doCreate(@PathVariable Long idtable, @RequestBody List<OrderItemMenuDto> listMenuOrders, @RequestParam String discount) {
        User user = userService.getByUsername(Principal.getPrincipal()).get();
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
            order.setUser(user);
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
        Optional<Order> opOrder = orderService.getByCoffeeTableId(idtable);
        if (opOrder.isPresent()) {
            Order order = opOrder.get();
            Bill bill = new Bill();
            bill.setTable(order.getCoffeeTable());
            bill.setSubAmount(order.getSubAmount());
            bill.setDiscount(order.getDiscount());
            bill.setTotalAmount(order.getTotalAmount());
            bill.setUser(user);
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
            CoffeeTable coffeeTable = coffeeTableService.findById(idtable).get();
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
        Optional<Order> opOldOrder = orderService.getByCoffeeTableId(oldTable);
        if (opOldOrder.isPresent()) {
            Order oldOrder = opOldOrder.get();
            Optional<Order> opNewOrder = orderService.getByCoffeeTableId(newTable);
            List<OrderItem> listOldOrder = orderItemService.findAllByOrder(oldOrder);
            if (opNewOrder.isPresent()) {
                Order newOrder = opNewOrder.get();
                for (OrderItemDto orderItemDto : listOrders) {
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
                    OrderItem orderItem = orderItemDto.toOderItem();
                    Drink drink = drinkService.findById(orderItemDto.getId()).get();
                    orderItem.setDrink(drink);
                    BigDecimal totalPrice = drink.getPrice().multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));
                    orderItem.setTotalPrice(totalPrice);
                    orderItem.setOrder(newOrder);
                    orderItemService.save(orderItem);
                }
                BigDecimal subPrice = orderItemService.calcSubAmount(newTable);
                Discount discount = null;
                int percentDiscount = 0;
                if (oldOrder.getDiscount() != null) {
                    discount = oldOrder.getDiscount();
                    percentDiscount = oldOrder.getDiscount().getPercentDiscount();
                    newOrder.setDiscount(oldOrder.getDiscount());
                } else {
                    newOrder.setDiscount(null);
                }
                newOrder.setSubAmount(subPrice);
                double valueDiscount = (double) percentDiscount / 100;
                BigDecimal totalAmount = subPrice.subtract(subPrice.multiply(BigDecimal.valueOf(valueDiscount)));
                newOrder.setTotalAmount(totalAmount);
                newOrder.setUser(user);
                orderService.save(newOrder);
                orderItemService.deleteAllByOrder(oldOrder);
                Order newOldOrder = oldOrder.newOrder();
                orderService.deleteOrderById(oldOrder.getId());
                orderService.save(newOldOrder);
                for (OrderItem orderItem : listOldOrder) {
                    Drink drink = drinkService.findById(orderItem.getDrink().getId()).get();
                    BigDecimal totalPrice = drink.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
                    orderItem.setTotalPrice(totalPrice);
                    orderItem.setOrder(newOldOrder);
                    orderItemService.save(orderItem);
                }
                if (discount == null) {
                    newOldOrder.setDiscount(null);
                } else {
                    newOldOrder.setDiscount(discount);
                }
                BigDecimal subPriceOld = orderItemService.calcSubAmount(newOldOrder.getId());
                newOldOrder.setSubAmount(subPriceOld);
                double valueDiscountOld = (double) percentDiscount / 100;
                BigDecimal totalAmountOld = subPriceOld.subtract(subPriceOld.multiply(BigDecimal.valueOf(valueDiscountOld)));
                newOldOrder.setTotalAmount(totalAmountOld);
                newOldOrder.setUser(user);
                orderService.save(newOldOrder);

                CoffeeTable oldTableGet = coffeeTableService.findById(oldTable).get();
                oldTableGet.setUsed(true);
                coffeeTableService.save(oldTableGet);
                CoffeeTable newTableGet = coffeeTableService.findById(newTable).get();
                newTableGet.setUsed(true);
                coffeeTableService.save(newTableGet);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
