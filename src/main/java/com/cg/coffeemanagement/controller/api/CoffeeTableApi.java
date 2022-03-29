package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.Cart;
import com.cg.coffeemanagement.model.CoffeeTable;
import com.cg.coffeemanagement.services.Cart.CartService;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tables")
public class CoffeeTableApi {
    @Autowired
    CoffeeTableService coffeeTableService;

    @Autowired
    AppUtil appUtil;

    @Autowired
    CartService cartService;


    @GetMapping("/{id}")
    public ResponseEntity<CoffeeTable> getById(@PathVariable Long id) {
        Optional<CoffeeTable> coffeeTable = coffeeTableService.findById(id);
        if (coffeeTable.isPresent()) {
            return new ResponseEntity<>(coffeeTable.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping()
    public ResponseEntity<List<CoffeeTable>> getAllTable() {
        List<CoffeeTable> coffeeTable = coffeeTableService.findAllNotDeleted();
        if (!coffeeTable.isEmpty()) {
            return new ResponseEntity<>(coffeeTable, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody CoffeeTable coffeetable, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Cart cart = new Cart();
        CoffeeTable createTable = coffeeTableService.save(coffeetable);
        cart.setCoffeeTable(createTable);
        cartService.save(cart);
        return new ResponseEntity<>(createTable, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> doUpdate(@PathVariable Long id,@Validated @RequestBody CoffeeTable coffeeTable,
                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
//        Long id = coffeeTable.getId();
        Optional<CoffeeTable> optionalCatalogDrink = coffeeTableService.findById(id);
        if (optionalCatalogDrink.isPresent()) {
            coffeeTableService.save(coffeeTable);
            Optional<CoffeeTable> updateCatalog = coffeeTableService.findById(id);
            return new ResponseEntity<>(updateCatalog.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Table's not valid");
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<?> doDelete(@RequestBody CoffeeTable coffeeTable) {
        Optional opCatalog = coffeeTableService.findById(coffeeTable.getId());
        if (opCatalog.isPresent()) {
            coffeeTableService.deleteCoffeeTableById(coffeeTable.getId());
//            coffeeTableService.deletedDrinkByCatalog(coffeeTable.getId());
            return new ResponseEntity<>(opCatalog.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Table's not valid");
        }
    }

    @PutMapping("/restore")
    public ResponseEntity<?> doRestore(@RequestBody CoffeeTable coffeeTable) {
        Optional opCatalog = coffeeTableService.findById(coffeeTable.getId());
        if (opCatalog.isPresent()) {
            coffeeTableService.restoreCoffeeTable(coffeeTable.getId());
            return new ResponseEntity<>(opCatalog.get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Catalog's not valid");
        }
    }

}
