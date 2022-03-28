package com.cg.coffeemanagement.services.Permission;

import com.cg.coffeemanagement.model.Permission;
import com.cg.coffeemanagement.services.IGeneralServices;

import java.util.List;
import java.util.Optional;

public interface IPermissionServices extends IGeneralServices<Permission> {
    @Override
    List<Permission> findAll();

    @Override
    Optional<Permission> findById(Long id);

    @Override
    Permission getById(Long id);

    @Override
    Permission save(Permission permission);

    @Override
    void remove(Long id);
}
