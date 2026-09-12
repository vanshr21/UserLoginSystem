package com.vansh.service;

import com.vansh.dao.UserDAO;
import com.vansh.model.User;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    UserDAO userDao;

    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    private Boolean validateFields(String... stringArgs) {
        for(String field : stringArgs) {
            if(field == null || field.trim().isBlank()) return false;
        }
        return true;
    }

    private Boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") && !userDao.getUserByEmail(email);

    }

    private Boolean validateUsername(String username) {
        return !userDao.getUserByUsername(username);
    }

    private Boolean validateLogin(String username, String password) {
        return userDao.getUserByUsernamePassword(username, password);
    }

    @Override
    public Map.Entry<Boolean, String> registerUser(String firstName, String lastName, String email, String username, String password, String confirmPassword) {
        if(!validateFields(firstName, lastName, email, username, password)) return new AbstractMap.SimpleEntry<>(false, "Fields are not filled");
        if(!validateEmail(email)) return new AbstractMap.SimpleEntry<>(false, "Invalid Email or Already used");
        if(!validateUsername(username)) return new AbstractMap.SimpleEntry<>(false, "Username Already used");
        if(!password.equals(confirmPassword)) return new AbstractMap.SimpleEntry<>(false, "Passwords Mismatched");
        if(!userDao.createUser(new User(firstName, lastName, email, username, password))) return new AbstractMap.SimpleEntry<>(false, "Error making user");
        return new AbstractMap.SimpleEntry<>(true, "Registration successful");
    }

    @Override
    public Map.Entry<Boolean, String> loginUser(String username, String password) {
        if(!validateFields(username, password)) return new AbstractMap.SimpleEntry<>(false, "Fields are not filled");
        if(!validateLogin(username, password)) return new AbstractMap.SimpleEntry<>(false, "Invalid Username or Password");
        return new AbstractMap.SimpleEntry<>(true, "Login successful");
    }
}
