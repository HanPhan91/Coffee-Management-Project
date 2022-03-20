package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.model.Drink;
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

    @GetMapping("/update/{id}")
    public ResponseEntity<?> getCatalogsUpdate(@PathVariable Long id) {
        List<Drink> newDrink = new ArrayList<>();
        List<List> response = new ArrayList<>();
        String name;
        List<Catalog> listCatalog = catalogService.findAllNotDeleted();
        Drink drink = drinkService.findById(id).get();
        newDrink.add(drink);
        response.add(newDrink);
        response.add(listCatalog);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody Drink drink,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
            List<Drink> newDrink = new ArrayList<>();
            List<String> catalogName = new ArrayList<>();
            List<List> respose = new ArrayList<>();
            String name;
            List<Catalog> listCatalog = catalogService.findAllNotDeleted();
            drink.setId(0L);
            Drink createDrink = drinkService.save(drink);
            newDrink.add(createDrink);
            for (Catalog catalog : listCatalog) {
//                need fix
                if (createDrink.getId().compareTo(catalog.getId()) == 0) {
                    name = catalog.getCatalogName();
                    catalogName.add(name);
                    break;
                }
            }
            respose.add(newDrink);
            respose.add(catalogName);
            return new ResponseEntity<>(respose, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody Drink drink, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Long id = drink.getId();
        Optional<Drink> optionalDrink = drinkService.findById(id);
        if (optionalDrink.isPresent()) {
            drinkService.save(drink);
            List<Drink> newDrink = new ArrayList<>();
            List<String> catalogName = new ArrayList<>();
            List<List> response = new ArrayList<>();
            String name;
            List<Catalog> listCatalog = catalogService.findAllNotDeleted();
            Drink updateDrink = drinkService.findById(id).get();
            newDrink.add(updateDrink);
            for (Catalog catalog : listCatalog) {
//                if (updateDrink.getIdCatalog().compareTo(catalog.getId()) == 0) {
//                    name = catalog.getCatalogName();
//                    catalogName.add(name);
//                    break;
//                }
            }
            response.add(newDrink);
            response.add(catalogName);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new DataInputException("Drink's not valid");
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<?> doDelete(@RequestBody Drink drink) {
        Optional opDrink = drinkService.findById(drink.getId());
        if (opDrink.isPresent()) {
            drinkService.deleteDrinkById(drink.getId());
            return new ResponseEntity<>(opDrink.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Customer's not valid");
        }
    }

    @PutMapping("/restore")
    public ResponseEntity<?> doRestore(@Validated @RequestBody Drink drink, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Long id = drink.getId();
        Optional<Drink> optionalDrink = drinkService.findById(id);
        if (optionalDrink.isPresent()) {
            List<Catalog> catalogDrinks = catalogService.findAllDeleted();
            for (Catalog catalog : catalogDrinks) {
                if (catalog.getId().compareTo(optionalDrink.get().getIdCatalog()) == 0) {
                    throw new DataInputException("Vui lòng kích hoạt danh mục " + catalog.getCatalogName() + " trước khi mở lại thức uống");
                }
            }
            drinkService.save(drink);
            drinkService.restoreDrink(id);
            Optional<Drink> updateDrink = drinkService.findById(id);
            return new ResponseEntity<>(updateDrink.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Drink's not valid");
        }

    }
}
