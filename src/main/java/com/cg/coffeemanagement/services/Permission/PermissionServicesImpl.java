package com.cg.coffeemanagement.services.Permission;

import com.cg.coffeemanagement.model.Permission;
import com.cg.coffeemanagement.repository.Permission.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermissionServicesImpl implements IPermissionServices{

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission getById(Long id) {
        return null;
    }

    @Override
    public Permission save(Permission permission) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
