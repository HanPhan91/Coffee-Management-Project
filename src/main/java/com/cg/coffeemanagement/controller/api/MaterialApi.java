package com.cg.coffeemanagement.controller.api;
import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.CatalogsMaterial;
import com.cg.coffeemanagement.model.Material;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Materials.IMaterialService;
import com.cg.coffeemanagement.services.users.IUserService;
import com.cg.coffeemanagement.utils.AppUtil;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialApi {
    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPosition(@PathVariable Long id) {
        Optional<Material> material = materialService.findById(id);
        if (material.isPresent()) {
            return new ResponseEntity<>(material.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody Material material, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
           // List<User> users = userService.findAll();
            User user=new User();
            user.setId(1L);
            material.setUser(user);
            Material createMaterial = materialService.save(material);
            System.out.println(createMaterial);
            return new ResponseEntity<>(createMaterial, HttpStatus.CREATED);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@Validated @RequestBody Material material, @PathVariable Long id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
            Optional<Material> optionalMaterial = materialService.findById(id);
            if (optionalMaterial.isPresent()) {
                Material editMaterial = optionalMaterial.get();
                editMaterial.setName(material.getName());
                editMaterial.setPrice(material.getPrice());
                editMaterial.setQuantity(material.getQuantity());
                materialService.save(editMaterial);

                return new ResponseEntity<>(materialService.findById(id).get(), HttpStatus.OK);
            } else {
                throw new DataInputException("nguyên liệu không tồn tại");
            }
        }
    }
}
