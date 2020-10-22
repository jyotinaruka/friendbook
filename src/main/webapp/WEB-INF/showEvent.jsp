<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Ideas</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="js/app.js"></script>

</head>
<body>
	<h1><c:out value="${event.name}"/></h1>
	
	<p>Posted By: <c:out value="${event.host.name}"/>
	<p>Date: <c:out value="${event.date}"/>
	<p>Location: <c:out value="${event.location}"/>
	
	<a href="/events/${event.id}/edit">Edit</a>
	<p><a href="/events">Back to Event List</a></p>
</body>
</html>