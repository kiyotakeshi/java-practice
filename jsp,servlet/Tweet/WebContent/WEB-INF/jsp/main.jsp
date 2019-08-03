<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Tsubuyaki Main</h1>
<p>
<c:out value="${loginUser.name}"/> is login-user
<a href="/Tsubuyaki/Logout">Logout</a>
</p>

<p><a href="/Tsubuyaki/Main">Renew</a></p>
<form action="/Tsubuyaki/Main" method="post">
<input type="text" name="text">
<input type="submit" value="tweet">
</form>

<%--  <c: if test="${not empty errorMsg }" >
	<p>${errorMsg }</p>
</c:if>
 --%>
 
<c:forEach var="mutter" items="${mutterList }">
	<p><c:out value="${mutter.userName }" />: <c:out value="${mutter.text }" /></p>
</c:forEach>
</body></html>