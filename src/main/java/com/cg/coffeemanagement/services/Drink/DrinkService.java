package com.cg.coffeemanagement.services.Drink;

import com.cg.coffeemanagement.model.Drink;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrinkService extends IGeneralServices<Drink> {

    void deleteDrinkById(@Param("id") Long id);

    void restoreDrink(@Param("id") Long id);

    List<Drink> findAllNotDeleted();

    List<Drink> findAllDeleted();

    Integer countDrink();

}
