<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>社員管理システム</title>
</head>

<body>
    <h1>社員管理システム</h1>

    <form action="./login" method="post">
        ${message}<br>
        ログインID <input type="text" name="loginId"><br>
        パスワード<input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>
</body>

</html>