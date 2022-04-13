package com.cg.coffeemanagement.services.Bill;

import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.repository.Bill.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public String incomeToday() {
        return billRepository.incomeToday();
    }

    @Override
    public String incomeToMonth() {
        return billRepository.incomeToMonth();
    }

    @Override
    public String billToday() {
        return billRepository.billToday();
    }
}
