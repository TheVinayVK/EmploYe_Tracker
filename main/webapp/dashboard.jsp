<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        h3 {
            color: #333;
            border-bottom: 1px solid #ccc;
            padding-bottom: 5px;
            margin-bottom: 20px;
        }

        p {
            text-align: center;
            margin-top: 10px;
            color: #666;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="date"],
        input[type="time"],
        input[type="text"],
        textarea,
        select {
            width: calc(100% - 22px); /* Adjust width to fit within the form */
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        textarea {
            height: 100px; /* Adjust height for textarea */
        }

        input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px 20px;
            cursor: pointer;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        td a {
            color: #007bff;
            text-decoration: none;
            margin-right: 10px;
        }

        td a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>Welcome, ${user.name}</h2>
    <p>Your role: ${user.roleID == 1 ? "Admin" : "Associate"}</p>

    <h3>Add Task</h3>
    <form action="tasks" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="userID" value="${user.userID}">
    
    <label for="projectID">Project:</label>
    <select id="projectID" name="projectID">
        <option value="1">Project A</option>
        <option value="2">Project B</option>
    </select>
    <br>
    
    <label for="taskDate">Task Date:</label>
    <input type="date" id="taskDate" name="taskDate" required>
    <br>
    
    <label for="startTime">Start Time:</label>
    <input type="time" id="startTime" name="startTime" required>
    <br>
    
    <label for="endTime">End Time:</label>
    <input type="time" id="endTime" name="endTime" required>
    <br>
    
    <label for="taskCategory">Task Category:</label>
    <input type="text" id="taskCategory" name="taskCategory" required>
    <br>
    
    <label for="description">Description:</label>
    <textarea id="description" name="description"></textarea>
    <br>
    
    <input type="submit" value="Add Task">
</form>
    <hr>

    <h3>Your Tasks</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Task ID</th>
                <th>Project</th>
                <th>Date</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Task Category</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>${task.taskID}</td>
                    <td>${task.projectID == 1 ? "Project A" : "Project B"}</td>
                    <td>${task.taskDate}</td>
                    <td>${task.startTime}</td>
                    <td>${task.endTime}</td>
                    <td>${task.taskCategory}</td>
                    <td>${task.description}</td>
                    <td>
                        <a href="tasks?action=edit&taskID=${task.taskID}">Edit</a>
                        <a href="tasks?action=delete&taskID=${task.taskID}&userID=${user.userID}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>