package com.cg.coffeemanagement.services.catalogsMaterial;

import com.cg.coffeemanagement.model.CatalogsMaterial;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICatalogsMaterialService extends IGeneralServices<CatalogsMaterial> {
     List<CatalogsMaterial> findByDeletedFalse();

     List<CatalogsMaterial> findByDeletedTrue();

    void deleteCatalogsMaterial(@Param("id") Long id);

    void restoreCatalogsMaterial(@Param("id") Long id);
}
