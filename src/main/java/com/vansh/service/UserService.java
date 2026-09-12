package com.vansh.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserService {
    Map.Entry<Boolean, String> registerUser(String firstName, String lastName, String email, String username, String password, String confirmPassword);
    Map.Entry<Boolean, String> loginUser(String username, String password);
}
