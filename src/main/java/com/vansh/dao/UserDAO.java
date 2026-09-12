package com.vansh.dao;

import com.vansh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDAO {
    private final DBConnection dbConnection;

    @Autowired
    public UserDAO(DBConnection dbConnection) throws SQLException{
        this.dbConnection = dbConnection;
    }

    //CREATE USER
    public boolean createUser(User user) {
        try (Connection connect = dbConnection.getConnection()) {
            PreparedStatement create = connect.prepareStatement("INSERT INTO user VALUE(?, ?, ?, ?, ?)");
            create.setString(1, user.getFirstName());
            create.setString(2, user.getLastName());
            create.setString(3, user.getEmail());
            create.setString(4, user.getUsername());
            create.setString(5, user.getPassword());

            int rows = create.executeUpdate();
            return rows > 0;

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Login
    public boolean getUserByUsernamePassword(String username, String password) {
        try(Connection connect = dbConnection.getConnection()) {
            PreparedStatement login = connect.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            login.setString(1, username);
            login.setString(2, password);
            ResultSet row = login.executeQuery();
            return row.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Get User by email
    public boolean getUserByEmail(String email) {
        try(Connection connect = dbConnection.getConnection()) {
            PreparedStatement getUser = connect.prepareStatement("SELECT * FROM user WHERE email = ?");
            getUser.setString(1, email);
            ResultSet rows = getUser.executeQuery();
            return rows.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Get User by username
    public boolean getUserByUsername(String username) {
        try(Connection connect = dbConnection.getConnection()) {
            PreparedStatement getUser = connect.prepareStatement("SELECT * FROM user WHERE username = ?");
            getUser.setString(1, username);
            ResultSet rows = getUser.executeQuery();
            return rows.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
