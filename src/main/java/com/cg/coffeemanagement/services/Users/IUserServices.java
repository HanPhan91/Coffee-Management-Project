package com.cg.coffeemanagement.services.Users;

import com.cg.coffeemanagement.model.Avatar;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.repository.query.Param;

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

    List<User> findByDeletedFalse();

    List<User> findByDeletedTrue();

    User create(UserDto userDto);


    User edit(User user ,UserDto userDto);

    void deleteUser(@Param("id") Long id);

    void restoreUser(@Param("id") Long id);
    Optional<User> getByUsername(String username);

    Optional<User> findUserDTOByUsername(String username);

    boolean existsByUsername(String username);

    void changePass(@Param("id") Long id, String pass);
    void saveAvatar( @Param("id")Long id, @Param("avatar") Avatar avatar);
}
