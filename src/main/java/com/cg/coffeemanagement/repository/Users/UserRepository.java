package com.cg.coffeemanagement.repository.Users;

<<<<<<< HEAD
import com.cg.coffeemanagement.model.Avatar;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.UserDto;
import org.springframework.data.domain.Sort;
=======
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.UserDto;
>>>>>>> han
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> han

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

<<<<<<< HEAD
    List<User> findByDeletedFalse(Sort createAt);

    List<User> findByDeletedTrue(Sort createAt);
=======
    List<User> findByDeletedFalse();

    List<User> findByDeletedTrue();
>>>>>>> han

    @Modifying
    @Query("UPDATE User u SET u.deleted = true WHERE u.id = :id")
    void deleteUser(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.deleted = false WHERE u.id = :id")
    void restoreUser(@Param("id") Long id);

<<<<<<< HEAD
    Optional<User> getByUsername(String username);

    @Query("SELECT NEW com.cg.coffeemanagement.model.dto.UserDto (u.id, u.username) FROM User u WHERE u.username= ?1")
    Optional<User> findUserDTOByUsername(String username);

    boolean existsByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.password= :pass WHERE u.id= :id")
    void changePass(@Param("id") Long id, String pass);

    @Modifying
    @Query("UPDATE User u SET u.avatar= :avatar WHERE u.id= :id")
    void saveAvatar(@Param("id") Long id, @Param("avatar") Avatar avatar);
=======
>>>>>>> han
}
