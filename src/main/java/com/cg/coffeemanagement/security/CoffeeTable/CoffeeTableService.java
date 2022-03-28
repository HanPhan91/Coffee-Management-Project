package com.cg.coffeemanagement.security.CoffeeTable;
import com.cg.coffeemanagement.model.CoffeeTable;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoffeeTableService extends IGeneralServices<CoffeeTable> {

    void deleteCoffeeTableById(@Param("id") Long id);

    void restoreCoffeeTable(@Param("id") Long id);

    List<CoffeeTable> findAllNotDeleted();

    List<CoffeeTable> findAllDeleted();

    Integer countCoffeeTable();
}
