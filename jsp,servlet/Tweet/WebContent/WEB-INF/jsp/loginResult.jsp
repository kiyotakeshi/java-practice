<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="model.User" %>
  <%
  //　セッションスコープからユーザ情報を取得
  User loginUser = (User) session.getAttribute("loginUser");
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>

<body>
<h1>Tsubuyaki Login</h1>
<% if(loginUser != null){ %>
	<p>Login sucess!</p>
	<p>Welcome <%= loginUser.getName() %></p>
	<a href="/Tsubuyaki/Main">Let's Tsubuyaki</a>
<% } else { %>
	<p>Login failed...</p>
	<a href="/Tsubuyaki/">go to Top...</a>
<% } %>
</body>
</html>