package com.employee.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.employee.dao.TaskDAO;
import com.employee.model.Task;

@WebServlet("/tasks")
public class TaskController extends HttpServlet {
    private TaskDAO taskDAO;

    public void init() throws ServletException {
        taskDAO = new TaskDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                handleAddTask(request, response);
            } else if ("edit".equals(action)) {
                handleEditTask(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                handleListTasks(request, response);
            } else if ("edit".equals(action)) {
                showEditForm(request, response);
            } else if ("delete".equals(action)) {
                handleDeleteTask(request, response);
            } else {
                handleListTasks(request, response);  // Default action is to list tasks
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }


    private void handleAddTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            int projectID = Integer.parseInt(request.getParameter("projectID"));
            LocalDate taskDate = LocalDate.parse(request.getParameter("taskDate"));
            LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
            LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));
            String taskCategory = request.getParameter("taskCategory");
            String description = request.getParameter("description");

            Task newTask = new Task(userID, projectID, taskDate, startTime, endTime, taskCategory, description);
            taskDAO.addTask(newTask);

            response.sendRedirect("tasks?action=list&userID=" + userID);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error adding task: " + e.getMessage());
            ((Object) request.getRequestDispatcher("dashboard.jsp")).forward(request, response);
        }
    }

    private void handleEditTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int taskID = Integer.parseInt(request.getParameter("taskID"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            int projectID = Integer.parseInt(request.getParameter("projectID"));
            LocalDate taskDate = LocalDate.parse(request.getParameter("taskDate"));
            LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
            LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));
            String taskCategory = request.getParameter("taskCategory");
            String description = request.getParameter("description");

            Task updatedTask = new Task(userID, projectID, taskDate, startTime, endTime, taskCategory, description);
            updatedTask.setTaskID(taskID);
            taskDAO.updateTask(updatedTask);

            response.sendRedirect("tasks?action=list&userID=" + userID);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating task: " + e.getMessage());
            ((Object) request.getRequestDispatcher("dashboard.jsp")).forward(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int taskID = Integer.parseInt(request.getParameter("taskID"));
            Task existingTask = (Task) taskDAO.getTaskByID(taskID);
            request.setAttribute("task", existingTask);
            request.getRequestDispatcher("editTask.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading task: " + e.getMessage());
            ((Object) request.getRequestDispatcher("dashboard.jsp")).forward(request, response);
        }
    }

    private void handleListTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            List<Task> tasks = taskDAO.getTasksByUserID(userID);
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error fetching tasks: " + e.getMessage());
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }

    private void handleDeleteTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int taskID = Integer.parseInt(request.getParameter("taskID"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            taskDAO.deleteTask(taskID);
            response.sendRedirect("tasks?action=list&userID=" + userID);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error deleting task: " + e.getMessage());
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }
}