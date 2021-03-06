package com.cg.coffeemanagement.services.Positions;

import com.cg.coffeemanagement.model.Permission;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.repository.Positions.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PositionServicesImpl implements IPositionServices{

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    @Override
    public Position getById(Long id) {
        return null;
    }

    @Override
    public Position save(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Position> findByDeletedFalse() {
        return positionRepository.findByDeletedFalse();
    }

    @Override
    public void deletePosition(Long id) {
        positionRepository.deletePosition(id);
    }

    @Override
    public List<Position> findByDeletedTrue() {
        return positionRepository.findByDeletedTrue();
    }

    @Override
    public void restorePosition(Long id) {
        positionRepository.restorePosition(id);
    }

    @Override
    public List<Position> findPositionNotDeletedAndPermissionSmaller(int permission) {
        return positionRepository.findPositionNotDeletedAndPermissionSmaller(permission);
    }
}
