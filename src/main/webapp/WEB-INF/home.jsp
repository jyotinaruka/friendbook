<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="header.jsp"%>

<div class="row">
	<div class="col-sm-12">

			<div class="card my-3">
				<form action="/home" method="post" class="card-body">
			      <h5 class="card-title">Create a post ...</h5>
				  <div class="input-group">
				    <textarea name="post" class="form-control"></textarea>
				    <div class="input-group-append">
				       <button class="btn btn-outline-secondary" type="submit">Post</button>
				    </div>
				  </div>					
				</form>
			</div>

		<c:forEach items="${allPosts }" var="post">
			<div class="card my-3">
			  <div class="card-body">
			    <h6 class="card-subtitle mb-2 text-muted">Posted by <c:out value="${post.postedBy.name}" /></h6>
			    <p class="card-text"><c:out value="${post.message}" /></p>
			  </div>
		<c:forEach items="${post.comments}" var="comment">
			  <div class="card-body">
				  <div class="card p-2">
				  <h6 class="card-subtitle mb-2 text-muted">Commented by <c:out value="${comment.commentedBy.name}" /></h6>
				  <p class="card-text"><c:out value="${comment.message}"></c:out></p>
				  </div>
			  </div>
		</c:forEach>
			    <div class="card-footer">
					<form action="/comment/${post.id}" method="post">
						  <div class="input-group">
						    <input name="message" class="form-control" />
						    <div class="input-group-append">
						       <button class="btn btn-outline-secondary" type="submit">Comment</button>
						    </div>
						  </div>					
					</form>
			    </div>
			</div>
			
			
		</c:forEach>



	</div>
</div>

<%@include file="footer.jsp"%>