package com.cg.coffeemanagement.services.Catalog;

import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CatalogService extends IGeneralServices<Catalog> {

    @Override
    List<Catalog> findAll();

    @Override
    Optional<Catalog> findById(Long id);

    @Override
    Catalog getById(Long id);

    @Override
    Catalog save(Catalog catalog);

    @Override
    void remove(Long id);

    void deleteCatalog(@Param("id") Long id);

    void restoreCatalog(@Param("id") Long id);

    List<Catalog> findAllNotDeleted();

    List<Catalog> findAllDeleted();

    void restoreDrinkByCatalog(@Param("idCatalog") Long idCatalog);

    void updateSummary(@Param("id") Long id, int summary);
}
