package com.cg.coffeemanagement.security.CoffeeTable;

import com.cg.coffeemanagement.model.CoffeeTable;
import com.cg.coffeemanagement.repository.CoffeeTable.CoffeeTableRepository;
import com.cg.coffeemanagement.services.CoffeeTable.CoffeeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CoffeeTableServiceImpl implements CoffeeTableService {

    @Autowired
    CoffeeTableRepository coffeeTableRepository;
    @Override
    public void deleteCoffeeTableById(Long id) {
        coffeeTableRepository.deleteCoffeeTableById(id);
    }

    @Override
    public void restoreCoffeeTable(Long id) {
        coffeeTableRepository.restoreCoffeeTable(id);
    }

    @Override
    public List<CoffeeTable> findAllNotDeleted() {
        return coffeeTableRepository.findAllNotDeleted();
    }

    @Override
    public List<CoffeeTable> findAllDeleted() {
        return coffeeTableRepository.findAllDeleted();
    }

    @Override
    public Integer countCoffeeTable() {
        return coffeeTableRepository.countCoffeeTable();
    }

    @Override
    public List<CoffeeTable> findAll() {
        return coffeeTableRepository.findAll();
    }

    @Override
    public Optional<CoffeeTable> findById(Long id) {
        return coffeeTableRepository.findById(id);
    }

    @Override
    public CoffeeTable getById(Long id) {
        return coffeeTableRepository.getById(id);
    }

    @Override
    public CoffeeTable save(CoffeeTable coffeeTable) {
        return coffeeTableRepository.save(coffeeTable);
    }

    @Override
    public void remove(Long id) {
        coffeeTableRepository.deleteCoffeeTableById(id);
    }
}
