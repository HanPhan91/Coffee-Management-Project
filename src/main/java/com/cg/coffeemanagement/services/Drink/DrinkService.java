package com.cg.coffeemanagement.services.Drink;

import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.model.Drink;
import com.cg.coffeemanagement.model.OrderItem;
import com.cg.coffeemanagement.model.dto.DrinkDto;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrinkService extends IGeneralServices<Drink> {

    void deleteDrinkById(@Param("id") Long id);

    void restoreDrink(@Param("id") Long id);

    List<Drink> findAllNotDeleted();

    List<Drink> findAllDeleted();

    Integer countDrink();

    boolean existsByName(String name);

    Drink create(DrinkDto drinkDto);

    Drink update(Drink drink, DrinkDto drinkDto);

    List<Drink> findAllByCatalog(Catalog catalog);

    void deleteDrinkByCatalog(@Param("idCatalog") Long idCatalog);
}
