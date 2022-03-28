package com.cg.coffeemanagement.controller.api;


import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.CatalogsMaterial;
import com.cg.coffeemanagement.model.Discount;
import com.cg.coffeemanagement.model.dto.CatalogsMaterialDto;
import com.cg.coffeemanagement.model.dto.DiscountDTO;
import com.cg.coffeemanagement.services.discount.IDiscountService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/discount")
public class DiscountApi {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IDiscountService discountService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscount(@PathVariable Long id) {
        Optional<Discount> discount = discountService.findById(id);
        if (discount.isPresent()) {
            return new ResponseEntity<>(discount.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody DiscountDTO discountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
            Discount createDiscount = discountService.save(discountDTO.toDiscount());
            return new ResponseEntity<>(createDiscount, HttpStatus.CREATED);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@Validated @RequestBody Discount discount, @PathVariable Long id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
            Optional<Discount> optionalDiscount = discountService.findById(id);
            if (optionalDiscount.isPresent()) {
                Discount editDiscount = optionalDiscount.get();
                editDiscount.setCode(discount.getCode());
                editDiscount.setDescription(discount.getDescription());
                editDiscount.setPercentDiscount(discount.getPercentDiscount());
                editDiscount.setCreatedAt(discount.getCreatedAt());
                editDiscount.setEndedAt(discount.getEndedAt());
                editDiscount.setQuantity(discount.getQuantity());
                Discount disEdit = discountService.save(editDiscount);
                return new ResponseEntity<>(disEdit, HttpStatus.OK);
            } else {
                throw new DataInputException("Khuyến mãi  không tồn tại");
            }
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id ,@RequestBody DiscountDTO discountDTO) {
        Optional<Discount> optionalDiscount = discountService.findById(id);
        if (optionalDiscount.isPresent()) {
            discountService.deleteDiscount(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new DataInputException("Danh mục nguyên liệu không tồn tại");
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> doRestore(@PathVariable Long id, @Validated @RequestBody DiscountDTO discountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<Discount> opDiscount = discountService.findById(id);
        if (opDiscount.isPresent()) {
            Discount restoreDiscount = opDiscount.get();
            restoreDiscount.setCode(discountDTO.getCode());
            discountService.save(restoreDiscount);
            discountService.restoreDiscount(restoreDiscount.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new DataInputException("Danh mục nguyên liệu không tồn tại");
        }
    }

}
