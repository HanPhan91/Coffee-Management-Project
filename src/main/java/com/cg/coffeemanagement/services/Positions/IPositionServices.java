package com.cg.coffeemanagement.services.Positions;

import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.services.IGeneralServices;

import java.util.List;

public interface IPositionServices extends IGeneralServices<Position> {
    public List<Position> findByDeletedFalse();
}
