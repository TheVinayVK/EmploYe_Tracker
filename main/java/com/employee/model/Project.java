package com.employee.model;

public class Project {
    private int projectID;
    private String projectName;

    public Project(int projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }

    public int getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}