package com.cg.coffeemanagement.repository.Catalog;

import com.cg.coffeemanagement.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {

    @Modifying
    @Query("UPDATE Catalog c SET c.deleted = true WHERE c.id= :id")
    void deleteCatalog(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Catalog c SET c.deleted = false WHERE c.id= :id")
    void restoreCatalog(@Param("id") Long id);

    @Query("SELECT c FROM Catalog c WHERE c.deleted = false")
    List<Catalog> findAllNotDeleted();

    @Query("SELECT c FROM Catalog c WHERE c.deleted = true")
    List<Catalog> findAllDeleted();

    @Modifying
    @Query("UPDATE Drink d SET d.deleted = false WHERE d.id= :idCatalog")
    void restoreDrinkByCatalog(@Param("idCatalog") Long idCatalog);

}
