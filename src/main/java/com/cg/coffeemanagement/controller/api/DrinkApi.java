package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.model.Drink;
import com.cg.coffeemanagement.model.dto.DrinkDto;
import com.cg.coffeemanagement.services.Catalog.CatalogService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drinks")
public class DrinkApi {

    @Autowired
    DrinkService drinkService;

    @Autowired
    AppUtil appUtil;

    @Autowired
    CatalogService catalogService;

    @GetMapping("/create")
    public ResponseEntity<?> getCatalogsCreate() {
        List<Catalog> listCatalog = catalogService.findAllNotDeleted();
        return new ResponseEntity<>(listCatalog, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getById(@PathVariable Long id) {
        Optional<Drink> drink = drinkService.findById(id);
        if (drink.isPresent()) {
            return new ResponseEntity<>(drink.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody DrinkDto drinkDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Drink drink = new Drink();
        drink.setName(drinkDto.getName());
        drink.setDescription(drinkDto.getDescription());
        drink.setPrice(drinkDto.getPrice());
        drink.setInventory(drinkDto.getInventory());
        drink.setCatalog(catalogService.findById(drinkDto.getCatalog()).get());
        Drink returnDrink = drinkService.save(drink);
//        returnDrink.setStorage(true);
        return new ResponseEntity<>(returnDrink, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> doUpdate(@PathVariable Long id,@Validated @RequestBody DrinkDto drinkDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<Drink> drink = drinkService.findById(id);
        if(drink.isPresent()){
            Drink drinkUp = drink.get();
            drinkUp.setName(drinkDto.getName());
            drinkUp.setDescription(drinkDto.getDescription());
            drinkUp.setPrice(drinkDto.getPrice());
            drinkUp.setInventory(drinkDto.getInventory());
            drinkUp.setCatalog(catalogService.findById(drinkDto.getCatalog()).get());
            Drink returnDrink = drinkService.save(drinkUp);
//            if(returnDrink.getInventory()>0){
//                returnDrink.setStorage(true);
//            }
            return new ResponseEntity<>(returnDrink, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/delete")
    public ResponseEntity<?> doDelete(@RequestBody DrinkDto drinkDto) {
        Optional opDrink = drinkService.findById(drinkDto.getId());
        if (opDrink.isPresent()) {
            drinkService.deleteDrinkById(drinkDto.getId());
            return new ResponseEntity<>(opDrink.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Customer's not valid");
        }
    }

//    @PutMapping("/restore")
//    public ResponseEntity<?> doRestore(@Validated @RequestBody Drink drink, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return appUtil.mapErrorToResponse(bindingResult);
//        }
//        Long id = drink.getId();
//        Optional<Drink> optionalDrink = drinkService.findById(id);
//        if (optionalDrink.isPresent()) {
//            List<Catalog> catalogDrinks = catalogService.findAllDeleted();
//            for (Catalog catalog : catalogDrinks) {
//                if (catalog.getId().compareTo(optionalDrink.get().getIdCatalog()) == 0) {
//                    throw new DataInputException("Vui lòng kích hoạt danh mục " + catalog.getCatalogName() + " trước khi mở lại thức uống");
//                }
//            }
//            drinkService.save(drink);
//            drinkService.restoreDrink(id);
//            Optional<Drink> updateDrink = drinkService.findById(id);
//            return new ResponseEntity<>(updateDrink.get(), HttpStatus.OK);
//        } else {
//            throw new DataInputException("Drink's not valid");
//        }
//
//    }
}
