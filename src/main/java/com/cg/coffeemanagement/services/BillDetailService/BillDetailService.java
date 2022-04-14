package com.cg.coffeemanagement.services.BillDetailService;

import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.model.BillDetail;
import com.cg.coffeemanagement.model.dto.SummaryDrink;
import com.cg.coffeemanagement.services.IGeneralServices;

import java.util.List;

public interface BillDetailService extends IGeneralServices<BillDetail> {
    List<BillDetail> findAllByBill(Bill bill);

    List<SummaryDrink> summaryDrinkInBillDetail();
}
