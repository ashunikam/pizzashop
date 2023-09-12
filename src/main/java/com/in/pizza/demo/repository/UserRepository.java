package com.in.pizza.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.pizza.demo.model.Login;
import com.in.pizza.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Login findByUsernameAndPassword(String username, String password);
}