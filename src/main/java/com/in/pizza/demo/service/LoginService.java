package com.in.pizza.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pizza.demo.model.Login;
import com.in.pizza.demo.repository.LoginRepository;





@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Login login(String username, String password) {
        return loginRepository.findByUsernameAndPassword(username, password);
    }
}