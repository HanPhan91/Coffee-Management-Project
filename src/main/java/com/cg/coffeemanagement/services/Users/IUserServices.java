package com.cg.coffeemanagement.services.Users;

import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.TempDTO;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.services.IGeneralServices;

import java.util.List;
import java.util.Optional;

public interface IUserServices extends IGeneralServices<User> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    User getById(Long id);

    @Override
    User save(User user);

    @Override
    void remove(Long id);

}
