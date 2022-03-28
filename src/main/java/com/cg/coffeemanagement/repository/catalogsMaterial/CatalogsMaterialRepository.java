package com.cg.coffeemanagement.repository.catalogsMaterial;

import com.cg.coffeemanagement.model.CatalogsMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogsMaterialRepository extends JpaRepository<CatalogsMaterial, Long> {

    List<CatalogsMaterial> findByDeletedFalse();

    List<CatalogsMaterial> findByDeletedTrue();

    @Modifying
    @Query("UPDATE CatalogsMaterial c SET c.deleted = true WHERE c.id = :id")
    void deleteCatalogsMaterial(@Param("id") Long id);

    @Modifying
    @Query("UPDATE CatalogsMaterial c SET c.deleted = false WHERE c.id = :id")
    void restoreCatalogsMaterial(@Param("id") Long id);
}
