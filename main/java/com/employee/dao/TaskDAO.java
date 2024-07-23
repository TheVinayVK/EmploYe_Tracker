package com.employee.dao;

import com.employee.model.Task;
import com.employee.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public List<Task> getTasksByUserID(int userID) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM Tasks WHERE UserID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("UserID"),
                        resultSet.getInt("ProjectID"),
                        resultSet.getDate("TaskDate").toLocalDate(),
                        resultSet.getTime("StartTime").toLocalTime(),
                        resultSet.getTime("EndTime").toLocalTime(),
                        resultSet.getString("TaskCategory"),
                        resultSet.getString("Description")
                );
                task.setTaskID(resultSet.getInt("TaskID"));
                tasks.add(task);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return tasks;
    }

    public Task getTaskByID(int taskID) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Task task = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM Tasks WHERE TaskID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                task = new Task(
                        resultSet.getInt("UserID"),
                        resultSet.getInt("ProjectID"),
                        resultSet.getDate("TaskDate").toLocalDate(),
                        resultSet.getTime("StartTime").toLocalTime(),
                        resultSet.getTime("EndTime").toLocalTime(),
                        resultSet.getString("TaskCategory"),
                        resultSet.getString("Description")
                );
                task.setTaskID(resultSet.getInt("TaskID"));
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return task;
    }

    public void addTask(Task task) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO Tasks (UserID, ProjectID, TaskDate, StartTime, EndTime, TaskCategory, Description) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getUserID());
            statement.setInt(2, task.getProjectID());
            statement.setDate(3, Date.valueOf(task.getTaskDate()));
            statement.setTime(4, Time.valueOf(task.getStartTime()));
            statement.setTime(5, Time.valueOf(task.getEndTime()));
            statement.setString(6, task.getTaskCategory());
            statement.setString(7, task.getDescription());
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public void updateTask(Task task) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE Tasks SET UserID = ?, ProjectID = ?, TaskDate = ?, StartTime = ?, EndTime = ?, TaskCategory = ?, Description = ? WHERE TaskID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getUserID());
            statement.setInt(2, task.getProjectID());
            statement.setDate(3, Date.valueOf(task.getTaskDate()));
            statement.setTime(4, Time.valueOf(task.getStartTime()));
            statement.setTime(5, Time.valueOf(task.getEndTime()));
            statement.setString(6, task.getTaskCategory());
            statement.setString(7, task.getDescription());
            statement.setInt(8, task.getTaskID());
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public void deleteTask(int taskID) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "DELETE FROM Tasks WHERE TaskID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskID);
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
}