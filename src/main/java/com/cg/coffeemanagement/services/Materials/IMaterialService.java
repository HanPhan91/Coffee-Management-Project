package com.cg.coffeemanagement.services.Materials;

import com.cg.coffeemanagement.model.Material;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMaterialService extends IGeneralServices<Material> {
    Page<Material> findByCatalogMaterialId(Long id, Pageable pageable);

//    List<Material> findByDeletedFalse();
}
