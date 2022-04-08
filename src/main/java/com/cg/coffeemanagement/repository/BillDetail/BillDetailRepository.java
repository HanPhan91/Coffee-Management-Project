package com.cg.coffeemanagement.repository.BillDetail;

import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {

    List<BillDetail> findAllByBill(Bill bill);
}
