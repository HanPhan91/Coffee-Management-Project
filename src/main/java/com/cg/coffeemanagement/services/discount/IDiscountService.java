package com.cg.coffeemanagement.services.discount;

import com.cg.coffeemanagement.model.Discount;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IDiscountService extends IGeneralServices<Discount> {
    List<Discount> findByDeletedFalse();

    List<Discount> findByDeletedTrue();

    Discount findDiscountActive(@Param("code") String code);

    Optional<Discount> findDiscountByCodeAndDeletedFalseAndQuantityIsGreaterThanAndEndedAtGreaterThanEqual(String code);

    void deleteDiscount(@Param("id") Long id);

    void restoreDiscount(@Param("id") Long id);

    void reduceQuantity(Discount discount);

    void updateQuantity(@Param("code") String code, @Param("quantity") int quantity);
}
