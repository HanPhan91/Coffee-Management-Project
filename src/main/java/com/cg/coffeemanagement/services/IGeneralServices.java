package com.cg.coffeemanagement.services;

import java.util.List;
import java.util.Optional;

public interface IGeneralServices <T>{
    List<T> findAll();

    Optional<T> findById(Long id);

    T getById(Long id);

    T save(T t);

    void remove(Long id);
}
