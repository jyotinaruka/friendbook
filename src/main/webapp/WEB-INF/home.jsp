<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Friendbook</title>
<link rel="stylesheet" href="/css/bootstrap.css" />
<link rel="stylesheet" href="/css/style.css" />

</head>
<body>
	<div id="wrapper">
	
		
		<a class="" href="/home"> Welcome <c:out value="${loginUser.name}" /></a>
		<a class="" href="/home"> Today's date is <c:out value="${date}" /></a>
		
		<form:form action="/posting" method="post" modelAttribute="dorm">
		<c:forEach items="${allPosts }" var="post">
		<form:label path="post">Post Something!</form:label>
		<form:errors path="post"></form:errors>
		<form:input path="post"></form:input>
		<input type="submit" value="Submit"/>
		</c:forEach>
		<c:forEach items="${allComments}" var="comment">
		<form:label path="comment">Leave a comment!</form:label>
		<form:errors path="comment"></form:errors>
		<form:input path="comment"></form:input>
		<input type="submit" value="Submit"/>
		</c:forEach>
		</form:form>
		
		

		
		
		<a href="/logout">Logout</a>
	</div>
</body>
</html>