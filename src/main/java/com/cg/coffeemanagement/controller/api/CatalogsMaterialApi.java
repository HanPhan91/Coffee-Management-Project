package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.CatalogsMaterial;
//import com.cg.coffeemanagement.model.dto.CatalogsMaterialDto;
import com.cg.coffeemanagement.model.dto.CatalogsMaterialDto;
import com.cg.coffeemanagement.services.catalogsMaterial.ICatalogsMaterialService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/catalogsMaterial")
public class CatalogsMaterialApi {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private ICatalogsMaterialService catalogsMaterialService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPosition(@PathVariable Long id) {
        Optional<CatalogsMaterial> catalogsMaterial = catalogsMaterialService.findById(id);
        if (catalogsMaterial.isPresent()) {
            return new ResponseEntity<>(catalogsMaterial.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody CatalogsMaterial catalogsMaterial, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
            CatalogsMaterial createCatalogsMaterial = catalogsMaterialService.save(catalogsMaterial);
            return new ResponseEntity<>(createCatalogsMaterial, HttpStatus.CREATED);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@Validated @RequestBody CatalogsMaterial catalogsMaterial, @PathVariable Long id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
            Optional<CatalogsMaterial> optionalCatalogsMaterial = catalogsMaterialService.findById(id);
            if (optionalCatalogsMaterial.isPresent()) {
                CatalogsMaterial editCatalogMaterial = optionalCatalogsMaterial.get();
                editCatalogMaterial.setName(catalogsMaterial.getName());
                catalogsMaterialService.save(editCatalogMaterial);

                return new ResponseEntity<>(catalogsMaterialService.findById(id).get(), HttpStatus.OK);
            } else {
                throw new DataInputException("danh mục nguyên liệu không tồn tại");
            }
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id ,@RequestBody CatalogsMaterialDto catalogsMaterialDto) {
        Optional<CatalogsMaterial> optionalCatalogsMaterial = catalogsMaterialService.findById(id);
        if (optionalCatalogsMaterial.isPresent()) {
            catalogsMaterialService.deleteCatalogsMaterial(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new DataInputException("Danh mục nguyên liệu không tồn tại");
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> doRestore(@PathVariable Long id, @Validated @RequestBody CatalogsMaterialDto catalogsMaterialDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<CatalogsMaterial> opCatalogsMaterial = catalogsMaterialService.findById(id);
        if (opCatalogsMaterial.isPresent()) {
            CatalogsMaterial restoreCatalogsMaterial = opCatalogsMaterial.get();
            restoreCatalogsMaterial.setName(catalogsMaterialDto.getName());
            catalogsMaterialService.save(restoreCatalogsMaterial);
            catalogsMaterialService.restoreCatalogsMaterial(restoreCatalogsMaterial.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new DataInputException("Danh mục nguyên liệu không tồn tại");
        }
    }
}
