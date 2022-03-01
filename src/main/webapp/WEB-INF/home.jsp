<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<!-- FOR Bootstrap CSS (SIMPLE) -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

	<!-- BOOTSTRAP CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

	<!-- MY OWN CSS -->
	<link rel="stylesheet" href="/css/home.css"/>
	

	<!-- FOR ANY BOOTSTRAP THAT USES JS OR jQuery-->
<!-- 	<script src="/webjars/jquery/jquery.min.js"></script> -->
<!-- 	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="/js/rain.js" defer></script>
	<title>Home</title>
</head>
<body class="bgLightBlack">
	<div class="package bgLightBlack">
		<div class="taskbar">
			<div class="marginLeft width70">
				<p class="white navbar-brand link">Welcome!</p>
			</div>
				
			<div class="col-md-5">
				<a class="links" href="/edit/user">Edit Profile</a>
				<a class="links" href="/make/order">Order</a>
				<a class="links" href="/logout">Logout - (${user.firstName} ${user.lastName})</a>
			</div>
		
		</div>
		
		<div id = "pizza">
		
		<h1 class="title_grad txt_grad title_bar">Welcome to Tony's Pizza!</h1>
		<br>
		<br>
		<h1 class = "headers1">Create your own custom pizza!</h1>
		<br>
		<h3 class = "headers">With our fresh mozzarella and classic marinara sauce</h3>
		<br>
		<h3 class = "headers">Over 50 different choices of toppings available!</h3>
		<br>
		<h3 class = "headers">Classic, Deep Dish, and stuffed crusts available!</h3>
		<br>
		<br>
		<h1 class = "headers">Click <span id = "yellow"><a href = "/make/order">here</a></span> to begin your order!</h1>
		<br>
		<br>
		<br>
		<br>
		<br>
		</div>
	</div>

</body>
</html>