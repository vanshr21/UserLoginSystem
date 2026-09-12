package com.vansh.dao;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {

   public Connection getConnection() throws SQLException {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         throw new SQLException("MySQL JDBC Driver not found", e);
      }
      return DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/user_login",
              System.getenv("DB_USERNAME"),
              System.getenv("DB_PASSWORD")
      );
   }
}
