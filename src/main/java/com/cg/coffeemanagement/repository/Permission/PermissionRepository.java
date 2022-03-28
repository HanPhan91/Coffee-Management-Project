package com.cg.coffeemanagement.repository.Permission;

import com.cg.coffeemanagement.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
