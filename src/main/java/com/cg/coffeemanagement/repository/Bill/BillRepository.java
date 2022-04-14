package com.cg.coffeemanagement.repository.Bill;


import com.cg.coffeemanagement.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {


    @Query(value = "SELECT SUM(total_amount) FROM bills o WHERE DAY(o.created_at) = DAY(CURDATE()) AND " +
            "MONTH(o.created_at) = MONTH(CURDATE()) AND YEAR(o.created_at) = YEAR(CURDATE()) ; ", nativeQuery = true)
    BigDecimal incomeToday();

    @Query(value = "SELECT SUM(total_amount) FROM bills o WHERE MONTH(o.created_at) = MONTH(CURDATE())" +
            "AND YEAR(o.created_at) = YEAR(CURDATE()); ", nativeQuery = true)
    BigDecimal incomeToMonth();

    @Query(value = "SELECT COUNT(id) FROM bills o WHERE DAY(o.created_at) = DAY(CURDATE()) \n" +
            "AND MONTH(o.created_at) = MONTH(CURDATE()) AND YEAR(o.created_at) = YEAR(CURDATE()) ;", nativeQuery = true)
    BigDecimal billToday();

}
