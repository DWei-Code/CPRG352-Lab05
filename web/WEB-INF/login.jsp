<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action="login">
            <label>Username:</label>
            <input type="text" name="user_name" >
            <br>
            <label>Password:</label>
            <input type="password" name="password">
            <br>
            <input type="submit" value="Log in">
            <p>${errorText}</p>
        </form>

    </body>
</html>
