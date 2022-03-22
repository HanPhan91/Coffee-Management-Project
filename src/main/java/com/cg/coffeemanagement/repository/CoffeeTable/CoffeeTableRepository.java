package com.cg.coffeemanagement.repository.CoffeeTable;

import com.cg.coffeemanagement.controller.CoffeeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CoffeeTableRepository extends JpaRepository<CoffeeTable,Long> {

    @Modifying
    @Query("UPDATE CoffeeTable ct SET ct.deleted = true WHERE ct.id= :id")
    void deleteCoffeeTableById(@Param("id") long id);

    @Modifying
    @Query("UPDATE CoffeeTable ct SET ct.deleted = false WHERE ct.id= :id")
    void restoreCoffeeTable(@Param("id") Long id);

    @Query("SELECT ct FROM CoffeeTable ct WHERE ct.deleted = false ORDER BY ct.id DESC")
    List<CoffeeTable> findAllNotDeleted();

    @Query("SELECT ct FROM CoffeeTable ct  WHERE ct.deleted = true ORDER BY ct.id DESC")
    List<CoffeeTable> findAllDeleted();

    @Query("SELECT COUNT(ct.id) from CoffeeTable ct WHERE ct.deleted = false")
    Integer countCoffeeTable();
}
