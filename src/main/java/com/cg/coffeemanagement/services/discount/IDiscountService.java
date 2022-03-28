package com.cg.coffeemanagement.services.discount;

import com.cg.coffeemanagement.model.Discount;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDiscountService extends IGeneralServices<Discount> {
    List<Discount> findByDeletedFalse();
    List<Discount> findByDeletedTrue();

    void deleteDiscount(@Param("id") Long id);
    void restoreDiscount(@Param("id") Long id);
}
