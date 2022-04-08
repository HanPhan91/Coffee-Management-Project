package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.model.BillDetail;
import com.cg.coffeemanagement.services.Bill.BillService;
import com.cg.coffeemanagement.services.BillDetailService.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bills")
public class BillApi {
    @Autowired
    BillService billService;
    @Autowired
    BillDetailService billDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBillItems(@PathVariable("id") Long id) {
        Optional<Bill> opBill = billService.findById(id);
        if (opBill.isPresent()) {
            Bill bill = opBill.get();
            List<BillDetail> listBillDetail = billDetailService.findAllByBill(bill);
            return new ResponseEntity<>(listBillDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
