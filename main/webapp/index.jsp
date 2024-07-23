<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <title>Login</title>
    
    <style>
body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    margin: 0;
    padding: 0;
}
.container {
    width: 300px; /* Adjust the width as per your preference */
    margin: 50px auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center; /* Center align the content within the container */
}

h2 {
    text-align: center;
    color: #333;
}

form {
    margin-top: 20px;
    text-align: left; /* Reset text-align for form contents to left-aligned */
}

label {
    display: block;
    margin-bottom: 8px;
    text-align: left; /* Align labels to the left */
}

input[type="email"],
input[type="password"],
input[type="submit"] {
    width: 100%; /* Make inputs and submit button full width */
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    display: block; /* Ensure inputs and submit button display as block elements */
    margin: 0 auto; /* Center align inputs and submit button within their container */
}

input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #45a049;
}

p {
    text-align: center;
    margin-top: 15px;
}

a {
    color: #007bff;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

</style>
</head>
<body>
 <h2>Login</h2>
    <form action="/EmployeeTimeTracker/auth" method="post">
        <input type="hidden" name="action" value="login">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
       <input type="submit" value="Login">
    </form>
    <p>Don't have an account? <a href="register.jsp">Register here</a>.</p>
    <p>${error}</p>
</body>
</html>