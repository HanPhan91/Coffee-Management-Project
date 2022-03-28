package com.cg.coffeemanagement.services.Drink;


import com.cg.coffeemanagement.model.Drink;
import com.cg.coffeemanagement.repository.Drink.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DrinkServiceImpl implements DrinkService{


    @Autowired
    DrinkRepository drinkRepository;

    @Override
    public void deleteDrinkById(Long id) {
            drinkRepository.deleteDrinkById(id);
    }

    @Override
    public void restoreDrink(Long id) {
        drinkRepository.restoreDrink(id);

    }

    @Override
    public List<Drink> findAllNotDeleted() {
        return drinkRepository.findAllNotDeleted();
    }

    @Override
    public List<Drink> findAllDeleted() {
        return drinkRepository.findAllDeleted();
    }

    @Override
    public Integer countDrink() {
        return drinkRepository.countDrink();
    }

    @Override
    public List<Drink> findAll() {
        return drinkRepository.findAll();
    }

    @Override
    public Optional<Drink> findById(Long id) {
        return drinkRepository.findById(id);
    }

    @Override
    public Drink getById(Long id) {
        return drinkRepository.getById(id);
    }

    @Override
    public Drink save(Drink drink) {
        return drinkRepository.save(drink);
    }

    @Override
    public void remove(Long id) {
        drinkRepository.deleteById(id);
    }
}
