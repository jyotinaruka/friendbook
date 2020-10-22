<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Event</title>
</head>
<body>
	<h1>Create a new Event</h1>
	
	<form:form action="/events" method="POST" modelAttribute="event">
	
		<div>
			<form:label path="eventname">Event name:</form:label>
			<form:errors path="eventname"/>
			<form:input path="eventname"/>
		</div>
	
		<div>
			<form:label path="date">Date:</form:label>
			<form:errors path="date"/>
			<form:input path="date" type="date" name="date"/>
		
		</div>
		
		<div>
			<form:label path="location">Location:</form:label>
			<form:errors path="location"/>
			<form:input path="location"/>
		</div>
	
	<input type="submit" value="Create"/>
	</form:form>
	<p><a href="/events">Back to Event List</a></p>
</body>
</html>