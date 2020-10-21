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
	<div id="homeWrapper">

	
		
		<a class="" href="/home"> Welcome <c:out value="${user.email}" /></a>
		<a class="" href="/home"> Today's date is <c:out value="${date}" /></a>
		
		<h2>Welcome back,<c:out value="${user.name }">!</c:out></h2>
		<h2>Today's Date is<c:out value="${date}"></c:out>and it is currently<c:out value="${time}"></c:out></h2>
		<a href="/logout">Logout</a>
		
		
<form action="/home" method="post">



<label>Create a Post!</label>
<input name="message"></input>
<input type="submit" value="Submit"/>
</form>

<form action="/comment/${post.id}" method="post">
<c:forEach items="${allPosts }" var="post">
<c:out value="${post.message}"></c:out>Posted by<c:out value="${post.postedBy.name}"></c:out>
<label>Add a Comment</label>
<input name="message"/>
<input type="submit" value="submit"/>
</form>
</c:forEach>


<c:forEach items="${post.comments }" var="comment">
<c:out value="${comment.name }"></c:out>
</c:forEach>
	
	
	
	
	
	
	
	
	
	
	
	
	
	</div>
</body>
</html>