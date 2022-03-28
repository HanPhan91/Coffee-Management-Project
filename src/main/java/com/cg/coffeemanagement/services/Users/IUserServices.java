package com.cg.coffeemanagement.services.Users;

<<<<<<< HEAD
import com.cg.coffeemanagement.model.Avatar;
=======
>>>>>>> han
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.TempDTO;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.services.IGeneralServices;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.UserDetailsService;
=======
>>>>>>> han

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
public interface IUserServices extends IGeneralServices<User>, UserDetailsService {
=======
public interface IUserServices extends IGeneralServices<User> {
>>>>>>> han
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

<<<<<<< HEAD
    User edit(User user, UserDto userDto);
=======
    User edit(User user ,UserDto userDto);
>>>>>>> han

    void deleteUser(@Param("id") Long id);

    void restoreUser(@Param("id") Long id);
<<<<<<< HEAD

    Optional<User> getByUsername(String username);

    Optional<User> findUserDTOByUsername(String username);

    boolean existsByUsername(String username);

    void changePass(@Param("id") Long id, String pass);
    void saveAvatar( @Param("id")Long id, @Param("avatar") Avatar avatar);
=======
>>>>>>> han
}
