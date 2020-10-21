<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="header.jsp"%>

<div class="row">
	<div class="col-4 mt-3">
		<img src="img/avatar.png" alt="" width="250" height="250" />
	</div>


	<div class="col mt-3">
		<h1>Jyoti Singh</h1>
		
		<%-- 
		<div class="form-inline">
				<a href="/message"><button type="submit" class=" btn-linear  btn-secondary p-1">Message</button></a>
				<a href="/edit"><button type="submit" class=" btn-linear  btn-secondary p-1">Edit</button></a>
		</div>
		--%>
		
		<h5>Intro</h5>
		<p><i class="ico fas fa-graduation-cap"></i> Studied at </p>
		<p><i class="ico fas fa-map-marker-alt"></i> Lives in Redmond, Washington</p>
		
		<div class="form-inline">
			<a href="/message"><button type="submit" class=" btn-linear  btn-secondary p-1">Message</button></a>
			<a href="/edit"><button type="submit" class=" btn-linear  btn-secondary p-1">Edit</button></a>
		</div>		
	</div>
	
</div>

<%@include file="footer.jsp"%>