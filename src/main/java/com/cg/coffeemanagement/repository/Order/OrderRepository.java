package com.cg.coffeemanagement.repository.Order;


import com.cg.coffeemanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


    @Query(value = "SELECT SUM(total_amount) FROM orders o WHERE DAY(o.created_at) = DAY(CURDATE()) AND " +
            "MONTH(o.created_at) = MONTH(CURDATE()) AND YEAR(o.created_at) = YEAR(CURDATE()) ; ", nativeQuery = true)
    String incomeToday();

    @Query(value = "SELECT SUM(total_amount) FROM orders o WHERE MONTH(o.created_at) = MONTH(CURDATE())" +
            "AND YEAR(o.created_at) = YEAR(CURDATE()); ", nativeQuery = true)
    String incomeToMonth();

    @Query(value = "SELECT COUNT(id) FROM orders o WHERE DAY(o.created_at) = DAY(CURDATE()) \n" +
            "AND MONTH(o.created_at) = MONTH(CURDATE()) AND YEAR(o.created_at) = YEAR(CURDATE()) ;", nativeQuery = true)
    String orderToday();

}
