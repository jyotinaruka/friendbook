<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="header.jsp"%>

<div class="row">
	<div class="col-4 mt-3">
		<img src="/img/avatar.png" alt="" width="250" height="250" />
	</div>


	<form:form action="/profile/edit" modelAttribute="editUserProfile" method="POST">
		<form:hidden path="id"/>
		<form:hidden path="user.id"/>
	<div class="col mt-3">
		<h1><c:out value="${loginUser.name}" /></h1>
		
		<%-- 
		<div class="form-inline">
				<a href="/message"><button type="submit" class=" btn-linear  btn-secondary p-1">Message</button></a>
				<a href="/edit"><button type="submit" class=" btn-linear  btn-secondary p-1">Edit</button></a>
		</div>
		--%>
		
		<div class="bg-danger text-white" role="alert">
			<form:errors path="editUserProfile.*"/>
		</div>
		
		<h5>Intro</h5>
		<p>
			<form:textarea path="bio"/>
		</p>
		<p><i class="ico fas fa-graduation-cap"></i> Studied at <form:input path="education"/></p>
		<p><i class="ico fas fa-map-marker-alt"></i> Lives in <form:input path="currentCity"/></p>
		
		<div class="form-inline">
			<%--<a href="/message"><button class=" btn-linear  btn-secondary p-1">Message</button></a>--%>
			<button type="submit" class=" btn-linear  btn-secondary p-1">Save</button>
		</div>		
	</div>
	</form:form>
	
</div>

<%@include file="footer.jsp"%>