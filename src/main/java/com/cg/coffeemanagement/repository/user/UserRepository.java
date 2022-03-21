package com.cg.coffeemanagement.repository.user;

import com.cg.coffeemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
