package com.cg.coffeemanagement.repository.material;

import com.cg.coffeemanagement.model.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Page<Material> findByCatalogsMaterialId(Long catalogsMaterialId, Pageable pageable);

    List<Material> findByDeletedFalse();
}
