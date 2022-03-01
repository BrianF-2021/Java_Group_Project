<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!-- Formatting (dates) --> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  

<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<!-- FOR Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" >
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/stylekaris.css">
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/stylemulti.css">
	<link rel="stylesheet" href="/css/jquery.multiselect.css">
	
	<link href=”https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css” rel=”stylesheet”>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>



	<title>Order History</title>
</head>

<body>
<div class="background-history">
<nav class="bg-dark p-2">
	<div class="row d-flex align-items-center justify-content-between p-3">
		<div class = "col-md-4">
			<a class="navbar-brand link" href="/home"> Tony's Pizza</a>
		</div>
		<div class = "col-md-5">
				<a class="link" href="/home">Home</a>
				<a class="link" href="/edit/user">Edit Profile</a>
				<a class="link" href="/logout">Logout - (${user.firstName} ${user.lastName})</a>
		</div>
	
	</div>
</nav>
<div class="container">
	 <h1 class="mt-4">Order History</h1>
	<c:forEach items="${orders}" var="order">
		<div class="row border border-2 border-secondary rounded shadow-sm d-flex p-3 mt-4 card-background">
			<div class="row">
				<h4><c:out value="Ordered on: ${order.getDateFormatted()} "/></h4>
			</div>
			
			<div class="col-md-8">
				
					<h6><strong>Size: </strong><c:out value="${order.size}" /></h6>
					<h6><strong>Crust: </strong><c:out value="${order.crust}" /></h6>
					<h6><strong>Toppings: </strong><c:out value="${order.toppings}" /></h6>
					<c:if test="${order.likers.size() > 0}">
					<h5 class="text-primary"><strong>Favorited!</strong></h5>
					</c:if>
			</div>
		
			
			<div class="col-md-4">
			<a href="/make/order" class="btn btn-primary mb-2 margin-right-2">Order Again</a>
			<c:if test="${order.likers.size() < 1}">
				<a href="/likeorder/${order.id}" class="btn btn-primary mb-2">Add Favorite</a>
			</c:if>
			<c:if test="${order.likers.size() > 0}">
				
				<a href="/unlikeorder/${order.id}" class="btn btn-danger mb-2">Remove Favorite</a>
			</c:if>
			<a href="/delete/order/${order.id}" class="btn btn-danger">Delete Order</a>
			</div>
		</div>
		</c:forEach>
</div>
</div>
</body>
</html>




