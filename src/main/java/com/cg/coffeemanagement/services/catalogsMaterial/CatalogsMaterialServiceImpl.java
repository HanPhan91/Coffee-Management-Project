package com.cg.coffeemanagement.services.catalogsMaterial;

import com.cg.coffeemanagement.model.CatalogsMaterial;
import com.cg.coffeemanagement.repository.catalogsMaterial.CatalogsMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatalogsMaterialServiceImpl implements ICatalogsMaterialService{

    @Autowired
    private CatalogsMaterialRepository catalogsMaterialRepository;

    @Override
    public List<CatalogsMaterial> findAll() {
        return catalogsMaterialRepository.findAll();
    }

    @Override
    public Optional<CatalogsMaterial> findById(Long id) {
        return catalogsMaterialRepository.findById(id);
    }

    @Override
    public CatalogsMaterial getById(Long id) {
        return catalogsMaterialRepository.getById(id);
    }

    @Override
    public CatalogsMaterial save(CatalogsMaterial catalogsMaterial) {
        return catalogsMaterialRepository.save(catalogsMaterial);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<CatalogsMaterial> findByDeletedFalse() {
        return catalogsMaterialRepository.findByDeletedFalse();
    }

    @Override
    public List<CatalogsMaterial> findByDeletedTrue() {
        return catalogsMaterialRepository.findByDeletedTrue();
    }

    @Override
    public void deleteCatalogsMaterial(Long id) {
        catalogsMaterialRepository.deleteCatalogsMaterial(id);

    }

    @Override
    public void restoreCatalogsMaterial(Long id) {
        catalogsMaterialRepository.restoreCatalogsMaterial(id);

    }
}
