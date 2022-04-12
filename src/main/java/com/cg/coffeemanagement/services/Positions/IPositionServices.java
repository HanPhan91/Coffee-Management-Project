package com.cg.coffeemanagement.services.Positions;

import com.cg.coffeemanagement.model.Permission;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPositionServices extends IGeneralServices<Position> {


    @Override
    List<Position> findAll();

    @Override
    Optional<Position> findById(Long id);

    @Override
    Position getById(Long id);

    @Override
    Position save(Position position);

    @Override
    void remove(Long id);

    public List<Position> findByDeletedFalse();

    public List<Position> findByDeletedTrue();

    void deletePosition(@Param("id") Long id);

    void restorePosition(@Param("id") Long id);

    List<Position> findPositionNotDeletedAndPermissionSmaller(@Param("permission") int permission);

}
