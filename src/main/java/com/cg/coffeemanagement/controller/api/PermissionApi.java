package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.model.Permission;
import com.cg.coffeemanagement.services.Permission.IPermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionApi {

    @Autowired
    private IPermissionServices permissionServices;

    @GetMapping
    public ResponseEntity<?> getPermission(){
        List<Permission> permissionList = permissionServices.findAll();
        return new ResponseEntity<>(permissionList, HttpStatus.OK);
    }
}
