package com.cg.coffeemanagement.services.Catalog;

import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.repository.Catalog.CatalogRepository;
import com.cg.coffeemanagement.services.Drink.DrinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService{

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    DrinkService drinkService;

    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Optional<Catalog> findById(Long id) {
        return catalogRepository.findById(id);
    }

    @Override
    public Catalog getById(Long id) {
        return catalogRepository.getById(id);
    }

    @Override
    public Catalog save(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public void deleteCatalog(Long id) {
        catalogRepository.deleteCatalog(id);
    }

    @Override
    public void restoreCatalog(Long id) {
        catalogRepository.restoreCatalog(id);
    }

    @Override
    public List<Catalog> findAllNotDeleted() {
        return catalogRepository.findAllNotDeleted();
    }

    @Override
    public List<Catalog> findAllDeleted() {
        return catalogRepository.findAllDeleted();
    }


    @Override
    public void restoreDrinkByCatalog(Long idCatalog) {
            catalogRepository.restoreDrinkByCatalog(idCatalog);
    }
}
