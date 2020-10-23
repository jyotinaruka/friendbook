<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pt" uri="http://ocpsoft.org/prettytime/tags" %>

<%@include file="header.jsp"%>

<div class="row">
	<div class="col-sm-12">
	<h1><c:out value="${event.eventname}"/></h1>
	
	<p>Posted By: <c:out value="${event.host.name}"/>
	<p>Date: <c:out value="${event.date}"/>
	<p>Location: <c:out value="${event.location}"/>
	
	<a href="/events/${event.id}/edit">Edit</a>
	<p><a href="/events">Back to Event List</a></p>
	
	</div>
</div>
<%@include file="footer.jsp"%>