package com.cg.coffeemanagement.repository.BillDetail;

import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.model.BillDetail;
import com.cg.coffeemanagement.model.dto.SummaryDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {

    List<BillDetail> findAllByBill(Bill bill);

    @Query("SELECT New com.cg.coffeemanagement.model.dto.SummaryDrink(b.drink, count(b.drink)) " +
            "FROM BillDetail b " +
            "GROUP BY b.drink " +
            "ORDER BY count(b.drink) DESC")
    List<SummaryDrink> summaryDrinkInBillDetail();

}
