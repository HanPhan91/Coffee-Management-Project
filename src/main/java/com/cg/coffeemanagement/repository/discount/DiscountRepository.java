package com.cg.coffeemanagement.repository.discount;

import com.cg.coffeemanagement.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long > {

    List<Discount> findByDeletedFalse();
    List<Discount> findByDeletedTrue();

    @Modifying
    @Query("UPDATE Discount d SET d.deleted = true WHERE d.id = :id")
    void deleteDiscount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Discount d SET d.deleted = false WHERE d.id = :id")
    void restoreDiscount(@Param("id") Long id);
}
