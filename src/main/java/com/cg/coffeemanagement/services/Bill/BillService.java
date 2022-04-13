package com.cg.coffeemanagement.services.Bill;

import com.cg.coffeemanagement.model.Bill;
import com.cg.coffeemanagement.services.IGeneralServices;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BillService extends IGeneralServices<Bill> {
    String incomeToday();
    String incomeToMonth();
    String billToday();
}
