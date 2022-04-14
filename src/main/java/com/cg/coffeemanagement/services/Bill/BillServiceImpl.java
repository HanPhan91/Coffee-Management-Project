package com.cg.coffeemanagement.services.Bill;

import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.repository.Bill.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Transactional
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill getById(Long id) {
        return null;
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public BigDecimal incomeToday() {
        return billRepository.incomeToday();
    }

    @Override
    public BigDecimal incomeToMonth() {
        return billRepository.incomeToMonth();
    }

    @Override
    public BigDecimal billToday() {
        return billRepository.billToday();
    }
}
