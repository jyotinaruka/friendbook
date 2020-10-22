<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FriendBook - Create Events</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="js/app.js"></script>    
</head>
<body>
	<h1>Welcome, <c:out value="${user.name}"/></h1>
	
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Date</th>
				<th>Location</th>
				<th>Host</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${events}" var="event">
			<tr>
				<td><a href="/events/${event.id}"><c:out value="${event.name}"/></a></td>
				<td><c:out value="${event.date}"/></td>
				<td><c:out value="${event.location}"/></td>
				<td><c:out value="${event.creator.name}"/></td>
				
				<td>
					<c:choose>
						<c:when test="${user == event.host}">
							<a href="/events/${event.id}/edit">Edit</a> | 
							<a href="/events/${event.id}/delete">Delete</a>
						</c:when>
						
						<c:when test="${event.users.contains(user)}">
							Joined <a href="/events/${event.id}/cancel">Cancel</a>
						</c:when>
						
						<c:otherwise>
							<a href="/events/${event.id}/join">Join</a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/tasks/new">Create a task</a>
</body>
</html>