package com.employee.dao;

import com.employee.model.User;
import com.employee.util.DBConnection;

import java.sql.*;

public class UserDAO {

    public User getUserByEmail(String email) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM Users WHERE Email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setUserID(resultSet.getInt("UserID"));
            user.setName(resultSet.getString("Name"));
            user.setEmail(resultSet.getString("Email"));
            user.setPasswordHash(resultSet.getString("PasswordHash"));
            user.setRoleID(resultSet.getInt("RoleID"));
            return user;
        }

        return null;
    }

    public void addUser(User user) throws SQLException {
        if (!isRoleIDExists(user.getRoleID())) {
            throw new SQLException("RoleID does not exist", "23000", 1452);
        }
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO Users (Name, Email, PasswordHash, RoleID) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPasswordHash());
        statement.setInt(4, user.getRoleID());
        statement.executeUpdate();
    }

    private boolean isRoleIDExists(int roleID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT 1 FROM roles WHERE RoleID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, roleID);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}