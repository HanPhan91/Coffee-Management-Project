package com.cg.coffeemanagement.services.Users;

import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.TempDTO;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralServices<User> {
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

    List<User> findByDeletedFalse();

    List<User> findByDeletedTrue();

    User create(UserDto userDto);

    User edit(User user ,UserDto userDto);

    void deleteUser(@Param("id") Long id);

    void restoreUser(@Param("id") Long id);
}
