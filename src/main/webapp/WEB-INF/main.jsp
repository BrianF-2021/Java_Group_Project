<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- FOR Bootstrap CSS (SIMPLE) -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

	<!-- BOOTSTRAP CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

	<!-- MY OWN CSS -->
	<link rel="stylesheet" href="/css/style.css"/>

	<!-- FOR ANY BOOTSTRAP THAT USES JS OR jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/javascript/javascript.js" defer></script>
	<title>Products and Categories Home</title>
</head>
<body class="bg-dark">
	<div class="d-flex flex-column w-100 bg-dark text-light">
		<h1 class="m-3 text-center title_grad txt_grad pb-3 border_bottom pt-3 border_top" >Tony's Pizza</h1>
		<div class="d-flex flex-row">
			<div class="container border border-dark" >
				<h1 class="m-3 pb-3 text-center border_bottom" >Register</h1>
				
			 	<div class="d-flex flex-column justify-content-center align-items-center mb-5">   
		
				    <form:form class="form-control w-75" method="POST" action="/registering" modelAttribute="user">
		   		        <p class="mb-3 mt-t">
				            <form:label path="firstName">First Name: </form:label>
				            <form:errors class="text-danger" path="firstName"/>
				            <form:input class="form-control" type="text" placeholder="First Name" path="firstName"/>
				        </p>
				        
				       	<p class="mb-3 mt-t">
				            <form:label path="lastName">Last Name: </form:label>
				            <form:errors class="text-danger" path="lastName"/>
				            <form:input class="form-control" type="text" placeholder="Last Name" path="lastName"/>
				        </p>
				        <p class="mb-3 mt-t">
				            <form:label path="email">Email: </form:label>
				            <form:errors class="text-danger" path="email"/>
				            <form:input class="form-control" type="email" placeholder="Email" path="email"/>
				        </p>
				        		   		        <p class="mb-3 mt-t">
				            <form:label path="address">Address: </form:label>
				            <form:errors class="text-danger" path="address"/>
				            <form:input class="form-control" type="text" placeholder="Address" path="address"/>
				        </p>
				        
				       	<p class="mb-3 mt-t">
				            <form:label path="city">City: </form:label>
				            <form:errors class="text-danger" path="city"/>
				            <form:input class="form-control" type="text" placeholder="City" path="city"/>
				        </p>
				        <p class="mb-3 mt-t">
				            <form:label path="state">State: </form:label>
				            <form:errors class="text-danger" path="state"/>
				            <form:input class="form-control" type="text" placeholder="State" path="state"/>
				        </p>
				        <p class="mb-3 mt-t">
				            <form:label path="password">Password: </form:label>
				            <form:errors class="text-danger" path="password"/>
				            <form:password class="form-control" placeholder="Password" path="password"/>
				        </p>
				        <p class="mb-3 mt-t">
				            <form:label path="passwordConfirmation">Confirm Password: </form:label>
				            <form:errors class="text-danger" path="passwordConfirmation"/>
				            <form:password class="form-control" placeholder="Password" path="passwordConfirmation"/>
				        </p>
				        <input class="btns" type="submit" value="Register"/>
				    </form:form>
			     </div>
			</div>
			
			<div class="container border border-dark" >
				<h1 class="m-3 text-center pb-3 border_bottom" >Login</h1>
				
				<div class="d-flex flex-column justify-content-center align-items-center mb-5">
				    <p class="text-danger" ><c:out value="${loginError}" /></p>
				    <form class="form-control w-75" method="post" action="/loggingIn">
				        <p class="mb-3 mt-t">
				            <label for="email">Email: </label>
				            <input class="form-control" placeholder="Email" type="text" id="email" name="email"/>
				        </p>
				        <p class="mb-3 mt-t">
				            <label for="password">Password: </label>
				            <input class="form-control" placeholder="Password" type="password" id="password" name="password"/>
				        </p>
				        <input class="btns" type="submit" value="Login"/>
				    </form>    
			    </div>
		    </div>
		    
	    </div>
    </div>
</body>
</html>

<h2>test for github, okay to delete this push, it is just a test</h2>

<%-- <c:out value="${user.email}"></c:out> --%>
<!-- 				        <p class="mb-3 mt-t"> -->
<%-- 				            <form:label path="picture">Picture: </form:label> --%>
<%-- 				            <form:errors class="text-danger" path="picture"/> --%>
<%-- 				            <form:input class="form-control" type="file" accept="image/png, image/jpeg" placeholder="Browse Folders" path="picture"/> --%>
<!-- 				        </p> -->

