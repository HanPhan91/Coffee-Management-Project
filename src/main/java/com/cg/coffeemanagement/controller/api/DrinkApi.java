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

    @GetMapping("/catalogs/{id}")
    public ResponseEntity<?> getDrinkByCatalog(@PathVariable Long id){
        Catalog catalog = catalogService.findById(id).get();
        List<Drink> drinkList = drinkService.findAllByCatalog(catalog);
        return new ResponseEntity<>(drinkList, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Drink>> showListDrink() {
        List<Drink> drink = drinkService.findAllNotDeleted();
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

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
    public ResponseEntity<?> doCreate(@Validated DrinkDto drinkDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        if (drinkService.existsByName(drinkDto.getName())) {
            throw new DataInputException("Th????c u????ng ??a?? t????n ta??i");
        }
        Drink drink = drinkService.create(drinkDto);
        Catalog catalog = catalogService.findById(drinkDto.getCatalog()).get();
        int summary = drinkService.countDrinkByCatalog(catalog);
        catalogService.updateSummary(catalog.getId(), summary);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> doUpdate(@PathVariable Long id, @Validated DrinkDto drinkDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<Drink> drink = drinkService.findById(id);
        if (drink.isPresent()) {
            Drink returnDrink = drinkService.update(drink.get(), drinkDto);
            return new ResponseEntity<>(returnDrink, HttpStatus.OK);
        } else {
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

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> doRestore(@PathVariable Long id , @Validated DrinkDto drinkDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<Drink> optionalDrink = drinkService.findById(id);
        if (optionalDrink.isPresent()) {
            List<Catalog> catalogDrinks = catalogService.findAllDeleted();
            for (Catalog catalog : catalogDrinks) {
                if (catalog.getId().compareTo(optionalDrink.get().getCatalog().getId()) == 0) {
                    throw new DataInputException("Vui l??ng k??ch ho???t danh m???c " + catalog.getCatalogName() + " tr?????c khi m??? l???i th???c u???ng");
                }
            }
            drinkService.update(optionalDrink.get(), drinkDto);
            drinkService.restoreDrink(id);
            Optional<Drink> updateDrink = drinkService.findById(id);
            return new ResponseEntity<>(updateDrink.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Drink's not valid");
        }
    }
}
