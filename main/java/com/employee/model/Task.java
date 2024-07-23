package com.employee.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private int taskID;
    private int userID;
    private int projectID;
    private LocalDate taskDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String taskCategory;
    private String description;

    public Task(int userID, int projectID, LocalDate taskDate, LocalTime startTime, LocalTime endTime, String taskCategory, String description) {
        this.userID = userID;
        this.projectID = projectID;
        this.taskDate = taskDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskCategory = taskCategory;
        this.description = description;
    }

    // Getters and setters
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}