package com.cg.coffeemanagement.repository.Positions;

import com.cg.coffeemanagement.model.Permission;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

     List<Position> findByDeletedFalse();

     List<Position> findByDeletedTrue();

    @Modifying
    @Query("UPDATE Position p SET p.deleted = true WHERE p.id = :id")
    void deletePosition(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Position p SET p.deleted = false WHERE p.id = :id")
    void restorePosition(@Param("id") Long id);

    @Query("SELECT p FROM Position p WHERE p.deleted = false AND p.permission.permissionAccess >= :permission")
    List<Position> findPositionNotDeletedAndPermissionSmaller(@Param("permission") int permission);

}
