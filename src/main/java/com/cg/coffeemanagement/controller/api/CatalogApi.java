package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.services.Catalog.CatalogService;
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
        } else {
            catalog.setId(0L);
            Catalog createDrink = catalogService.save(catalog);
            return new ResponseEntity<>(createDrink, HttpStatus.CREATED);
        }
    }
}
