package com.cg.coffeemanagement.services.Materials;

import com.cg.coffeemanagement.model.Material;
import com.cg.coffeemanagement.repository.material.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MaterialServiceImpl implements IMaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    @Override
    public Material getById(Long id) {
        return materialRepository.getById(id);
    }

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Page<Material> findByCatalogMaterialId(Long catalogsMaterialId, Pageable pageable) {
        return materialRepository.findByCatalogsMaterialId(catalogsMaterialId, pageable);
    }

    @Override
    public List<Material> findByDeletedFalse() {
        return materialRepository.findByDeletedFalse();
    }
}
