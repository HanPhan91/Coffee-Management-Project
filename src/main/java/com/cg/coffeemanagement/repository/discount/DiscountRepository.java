package com.cg.coffeemanagement.repository.discount;

import com.cg.coffeemanagement.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long > {

    List<Discount> findByDeletedFalse();
    List<Discount> findByDeletedTrue();

    @Query(value = "SELECT * FROM discounts as d where d.code = :code and d.quantity >0 and d.endedAt >= CURDATE()", nativeQuery = true)
    Discount findDiscountActive(@Param("code") String code);

    Optional<Discount> findDiscountByCodeAndDeletedFalseAndQuantityIsGreaterThanAndEndedAtGreaterThanEqual(String code, Integer quantity, Date date);

    @Modifying
    @Query("UPDATE Discount d SET d.quantity= :quantity WHERE d.code = :code")
    void updateQuantity(@Param("code") String code, @Param("quantity") int quantity);

    @Modifying
    @Query("UPDATE Discount d SET d.deleted = true WHERE d.id = :id")
    void deleteDiscount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Discount d SET d.deleted = false WHERE d.id = :id")
    void restoreDiscount(@Param("id") Long id);

    Integer countByDeletedFalseAndQuantityIsGreaterThanAndEndedAtGreaterThanEqual(Integer quantity, Date date);

}
