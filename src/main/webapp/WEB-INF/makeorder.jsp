
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



	<title>Order Pizza</title>
</head>


<body>
<div class="background">
<nav class="bg-dark p-2">
	<div class="row d-flex align-items-center justify-content-between p-3">
		<div class = "col-md-4">
			<a class="navbar-brand link" href="/home"> Tony's Pizza</a>
		</div>
		<div class = "col-md-5">
				<a class="link" href="/home">Home</a>
				<a class="link" href="/edit/user">Edit Profile</a>
				<a class="link" href="/order/history">Order History</a>
				<a class="link" href="/logout">Logout - (${user.firstName} ${user.lastName})</a>
		</div>
	
	</div>
</nav>
<div class="container">
  
		<h1 class="title_grad txt_grad title_bar">Make an Order</h1>
		
		<div class="d-flex justify-content-center mt-4">
			<div class = "row border border-secondary p-4 rounded marg-bottom-10 card-background">
				<div class="col">
					<h2 class="text-center" > ~ <span class="text-center bold">Personalize Your Pizza</span> ~ </h2>
			

			<form:form action="/making/order" method="POST" modelAttribute="order">
				<div class="form-group col-md-12 mt-4">
					<form:errors class="text-danger" path="method"/>
					<div class = "row align-items-center">
						<div class = "col-md-3">
							<form:label class="" path="method">Method: </form:label>
						</div>
						<div class = "col-md-6">
							<form:select class="form-control" type="text" path="method" placeholder="Takeout, delivery, dine-in">
								<option value="" disabled selected>--Select Method--</option>
								<option value="Takeout">Takeout</option>
	        					<option value="Delivery">Delivery</option>
	        					<option value="Dine-in">Dine-in</option>
							</form:select>
						</div>
					</div>
				</div>
			
			
				<div class="form-group col-md-12 mt-3">
					<form:errors class="text-danger" path="size"/>
					<div class = "row align-items-center">
						<div class = "col-md-3">
							<form:label  path="size">Size: </form:label>
						</div>
						<div class = "col-md-6">
							<form:select class="form-control" type="text" path="size" placeholder="Small, Medium, Large, X-Large">
								<option value="" disabled selected>--Select Size--</option>
								<option value="Small">Small</option>
	        					<option value="Medium">Medium</option>
	        					<option value="Large">Large</option>
	        					<option value="X-large">X-Large</option>
							</form:select>
						</div>
					</div>
				</div>
			
				
				<div class="form-group col-md-12 mt-3">
					<form:errors class="text-danger" path="crust"/>
					<div class = "row align-items-center">
						<div class = "col-md-3">
							<form:label path="crust">Crust: </form:label>
						</div>
						<div class = "col-md-6">
							<form:select class="form-control mbd-select md-form" type="text" path="crust">
								<option value="" disabled selected>--Select Crust--</option>
								<option value="Deep Dish">Deep Dish</option>
	        					<option value="Thin">Thin</option>
	        					<option value="Thick">Thick</option>
	        					<option value="Stuffed">Stuffed</option>
	        					<option value="Gluten-Free">Gluten Free</option>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="form-group col-md-12 mt-3">
					<form:errors class="text-danger" path="quantity"/>
					<div class = "row align-items-center">
						<div class = "col-md-3">
							<form:label class="color-bl label txt-underline" path="quantity">Quantity: </form:label>
						</div>
						<div class = "col-md-4">
							<form:input class="form-control" type="number" min="1" path="quantity" placeholder="QTY"/>
						</div>
					</div>
				</div>
				
			
				<div class="form-group col-md-12 mt-3">
			
					<div class="container text-left">
					<div class = "row justify-content-center">
						<div class = "col-md-10">
							<form:errors class="text-danger" path="toppings"/>
							<form:select path="toppings" name="basic[]" multiple="multiple" class="3col active form-control">
								<option value=" Tomato Sauce">Tomato Sauce</option>
								<option value=" Pesto">Pesto Sauce</option>
								<option value=" Mozzarella">Mozzarella Cheese</option>
	        					<option value=" Cheddar Cheese">Cheddar Cheese</option>
								<option value=" Feta Cheese">Feta Cheese</option>
								<option value=" Parmesan">Parmesan Cheese</option>
								<option value=" Tomates">Tomates</option>
	        					<option value=" Onions">Onions</option>
	        					<option value=" Peppers">Peppers</option>
	        					<option value=" Pineapple">Pineapple</option>
	        					<option value=" Spinach">Spinach</option>
	        					<option value=" Garlic">Garlic</option>
	        					<option value=" Mushrooms">Mushrooms</option>
	        					<option value=" Sausage">Sausage</option>
	        					<option value=" Pepperoni">Pepperoni</option>
	        					<option value=" Ham">Ham</option>	
	        					<option value=" Bacon">Bacon</option>
	        					<option value=" Chicken">Chicken</option>
	        				
							</form:select>
						</div>
						
					</div>
					</div>
				</div>
			

				
				<div class="d-flex flex-row justify-content-around m-3">
					<form:hidden path="price" value="12.50"/>
					<form:hidden path="user" value="${user.id}"/>
					<input class="btn btn-primary" type="submit" value="Submit Order"/>
					<a class="btn btn-danger"  href="/home">Cancel</a>
				</div>
				 
			</form:form>
		</div>
	</div>

</div>
</div>
</div>
	
 <script src="javascript/jquery-3.3.1.min.js"></script>
 <script src="/javascript/popper.min.js"></script>
    <script src="/javascript/bootstrap.min.js"></script>
    <script src="/javascript/jquery.multiselect.js"></script>
    <script src="/javascript/main.js"></script>




</body>
</html>

					
<!-- 		"/Users/karishanson/Dropbox/My Mac (Kariss-Air)/Downloads/multiselect-15/js/jquery-3.3.1.min.js"					<option value="java">Java</option> -->
<!-- 							<option value="python">Python</option> -->
<!-- 							<option value="javascript">javascript</option> -->
<!-- 							<option value="C">C</option> -->
<!-- 							<option value="C#">C#</option> -->
					

<%-- 			<form:hidden path="user" value="${user}"/> --%>