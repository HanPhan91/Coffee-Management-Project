package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.services.Catalog.CatalogService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogApi {

    @Autowired
    CatalogService catalogService;

    @Autowired
    DrinkService drinkService;

    @Autowired
    private AppUtil appUtil;

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> getById(@PathVariable Long id) {
        Optional<Catalog> catalog = catalogService.findById(id);
        if (catalog.isPresent()) {
            return new ResponseEntity<>(catalog.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody Catalog catalog, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

            Catalog createDrink = catalogService.save(catalog);
            return new ResponseEntity<>(createDrink, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody Catalog catalog,
                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Long id = catalog.getId();
        Optional<Catalog> optionalCatalogDrink = catalogService.findById(id);
        if (optionalCatalogDrink.isPresent()) {
            catalogService.save(catalog);
            Optional<Catalog> updateCatalog = catalogService.findById(id);
            return new ResponseEntity<>(updateCatalog.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Catalog's not valid");
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<?> doDelete(@RequestBody Catalog catalog) {
        Optional opCatalog = catalogService.findById(catalog.getId());
        if (opCatalog.isPresent()) {
            catalogService.deleteCatalog(catalog.getId());
            drinkService.deleteDrinkByCatalog(catalog.getId());
            return new ResponseEntity<>(opCatalog.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Catalog's not valid");
        }
    }

    @PutMapping("/restore")
    public ResponseEntity<?> doRestore(@RequestBody Catalog catalog) {
        Optional<Catalog> opCatalog = catalogService.findById(catalog.getId());
        if (opCatalog.isPresent()) {
            Catalog catalogRestore = opCatalog.get();
            catalogRestore.setCatalogName(catalog.getCatalogName());
            catalogService.save(catalogRestore);
            catalogService.restoreCatalog(catalogRestore.getId());
            return new ResponseEntity<>(opCatalog.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Catalog's not valid");
        }
    }
}
