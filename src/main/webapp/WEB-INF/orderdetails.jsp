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
	<link rel="stylesheet" href="/css/scottsStyle.css"/>

	<!-- FOR ANY BOOTSTRAP THAT USES JS OR jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/javascript/javascript.js" defer></script>
	<title>Order Details</title>
</head>
<body>
<div id="page">
	<div id="headerWrapper">
			<div id="logoWrapper">
				<h2>Tony's Pizza</h2>
			</div>
				<a class="links" href="/home">Home</a>
				<a class="links" href="/edit/user">Edit Profile</a>
				<a class="links" href="/make/order">Order</a>
				<a class="links" href="/logout">Logout <span>- (${user.firstName} ${user.lastName}) </span></a>
	</div><br>	
		<h1 class="title_grad txt_grad title_bar" >Order Confirmed</h1>
		<hr>
		<div>
		</div>

				<div id="showOrderWrapper">
					<h2 class="text-center" > ~ <span>Order Details</span> ~ </h2>
					<hr>
					<div>
						<h3>Name: ${order.user.firstName} ${order.user.lastName}</h3>
					</div>
					<div>
						<h3>Method: ${order.method}</h3>
					</div>

					<div>
						<h3>Crust: ${order.crust}</h3>
					</div>
					<div>
						<h3>Size: ${order.size}</h3>
					</div>
					<div>
						<h3>Toppings: ${order.toppings}</h3>
					</div>
					<div>
						<h3>Price: ${order.price}</h3>
					</div>

					<c:if test="${user.id == algo.user.id}">
						<div>
							<a class="btns" href="/edit/order/${order.id}">Edit</a>
							<a class="btns text-danger" href="/delete/algo/${order.id}">Delete</a>
						</div>
					</c:if><br>
					    <div class="d-flex flex-row justify-content-around m-3">
				        	<a class="button1" href="/make/order">Order Again!</a>
				        	<a class="button" href="/home">Home</a>
				    	</div>
				</div>
				<br>
				
				<div id="showDeliveryDetails">
					<h2 class="text-center" ><span> ~Your Delivery Info ~<br>(if delivery was method)</span></h2>
					<hr>
					<div>
						<h3>Name: ${order.user.firstName} ${order.user.lastName}</h3>
					</div>
					<div>
						<h3>Address: ${order.user.address}</h3>
					</div>

					<div>
						<h3>City: ${order.user.city}</h3>
					</div>
					<div>
						<h3>State: ${order.user.state}</h3>
					</div>
						</div>
			</div>
		
</body>
</html>

				
<%-- 				<c:if test="${hasCommented == false}"> --%>
<!-- 					<div class="d-flex flex-column p-4"> -->
<!-- 						<h2 class="txt-underline font-size mb-3 color-bl">Add Comment: </h2> -->
<%-- 						<form:form action="/add/comment/${algo.id}" method="POST" modelAttribute="comment"> --%>
<!-- 							<p> -->
<%-- 								<form:label class="black label text-light" path="thisComment"></form:label> --%>
<%-- 								<form:errors class="text-danger" path="thisComment"/> --%>
<%-- 								<form:textarea class="commentbox ms-3 border width90 bg-dark color-gr" type="text" path="thisComment"/> --%>
<!-- 							</p> -->
<%-- 							<form:hidden path="user" value="${user.id}"/> --%>
<%-- 							<form:hidden path="algo" value="${algo.id}"/> --%>
<!-- 							<input class="btns m-3" type="submit" value="Submit"/> -->
<%-- 						</form:form> --%>
<!-- 					</div> -->
<%-- 				</c:if> --%>
<!-- 			</div> -->
			
<!-- 			<div class="d-flex flex-column w-50 p-3 h-100 overFlowScroll"> -->
<!-- 				<h2 class="bold txt-underline text-center font-size color-bl">Comments</h2> -->
<!-- 				<hr> -->
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${algo.comments.size()>0}"> --%>
<%-- 						<c:forEach  items="${algo.comments}" var="comment"> --%>
<!-- 							<p class="txt-left border m-3 min-h200 overFlowScroll "> -->
<%-- 							<span class="font-size txt-underline color-org text-secondary" >${comment.user.firstName} ${comment.user.lastName}</span> -  --%>
<%-- 							<c:if test="${user.id == comment.user.id}"> --%>
<%-- 								<span> <a class="text-danger" href="/edit/comment/${comment.id}">Edit</a></span> |  --%>
<%-- 								<span> <a class="text-danger" href="/delete/comment/${comment.id}/${algo.id}">Delete</a></span>  --%>
<%-- 							</c:if> --%>
<%-- 							<span class="color-gr">- ${comment.thisComment} </span> --%>
<!-- 							</p> -->
							
<%-- 						</c:forEach> --%>
<%-- 					</c:when> --%>
<%-- 					<c:when test="${algo.comments.size()==0}"> --%>
<!-- 						<p class="text-center bold color-gr">There are no comments for this Algo!</p> -->
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
<!-- 			</div> -->
			






<!-- 							<a class="btns"  href="/home">Cancel</a> -->
		
<!-- ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ		 -->
		
<!-- 		<div class="container mt-3 pb-3">  -->
<%-- 		  <h2>Algo Comments (${algos.comments.size()): }</h2>          --%>
<!-- 		  <table class="table table-striped overFlowScroll"> -->
<!-- 			<thead> -->
<!-- 			  <tr> -->
<!-- 			    <th>Name</th> -->
<!-- 			    <th>Rating</th> -->
<!-- 			  </tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 				<c:forEach items="${show.ratings}" var="rating"> --%>
<!-- 				  <tr> -->
<%-- 				    <td> <p> ${rating.user.firstName} ${rating.user.lastName} </p> </td> --%>
<%-- 				    <td>${rating.rating}</td> --%>
<!-- 				  </tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</tbody> -->
<!-- 		  </table> -->
<!-- 		  <hr> -->
<!-- 		 </div> -->
		  
<!-- 		  zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz -->
<!-- 		  <div class="d-flex flex-row"> -->
<%-- 		  		<c:choose>  --%>
<%-- 			  	<c:when test="${haveRated == true}"> --%>
<%-- 			  	true ${usersRating.id}" --%>
<%-- 			  		<p class="me-5 bold" >You Have rated this ${usersRating.rating}</p> --%>
<%-- 			  		<form:form class="border w-50" action="/rate/update/${usersRating.id}" method="POST" modelAttribute="rating"> --%>
<!-- 						<p> -->
<%-- 							<form:label class="black label" path="rating">Rating: </form:label> --%>
<%-- 							<form:errors class="text-danger" path="rating"/> --%>
<%-- 							<form:select path="rating"> --%>
<!-- 								<option value=1>1</option> -->
<!-- 								<option value=2>2</option> -->
<!-- 								<option value=3>3</option> -->
<!-- 								<option value=4>4</option> -->
<!-- 								<option value=5>5</option> -->
<%-- 							</form:select> --%>
<!-- 						</p> -->
<%-- 						<form:hidden path="user" value="${usersRating.user.id}"/> --%>
<%-- 						<form:hidden path="show" value="${usersRating.show.id}"/> --%>
<!-- 						<input class="btns" type="submit" value="Rate"/> -->
<%-- 					</form:form> --%>
<%-- 				</c:when> --%>
<%-- 				<c:otherwise> --%>
<%-- 				<p> false userRating.id = ${usersRating.id} | show_id = ${show.id} | userRating.user.id = ${userRating.user.id} </p> --%>
<%-- 					<form:form class="border w-50" action="/rate/${show.id}" method="POST" modelAttribute="rating"> --%>
<!-- 						<p class="w-50"> -->
<%-- 							<form:label class="black label" path="rating">Rating: </form:label> --%>
<%-- 							<form:errors class="text-danger" path="rating"/> --%>
<%-- 							<form:select path="rating"> --%>
<!-- 								<option value=1>1</option> -->
<!-- 								<option value=2>2</option> -->
<!-- 								<option value=3>3</option> -->
<!-- 								<option value=4>4</option> -->
<!-- 								<option value=5>5</option> -->
<%-- 							</form:select> --%>
<!-- 						</p> -->
<%-- 						<form:hidden path="user" value="${user.id}"/> --%>
<%-- 						<form:hidden path="show" value="${show.id}"/> --%>
<!-- 						<input class="btns" type="submit" value="Rate"/> -->
<%-- 					</form:form> --%>
<%-- 				</c:otherwise> --%>
<%-- 				</c:choose> --%>
	
<!-- 		  <div class="d-flex flex-row"> -->
<!-- 		  ##########################################33 -->
		  
<%-- 		  	<c:forEach items="${show.ratings}" var="rate"> --%>
<%-- 			  	<c:if test="${rate.user == user}"> --%>
<%-- 			  		<p class="me-5 bold" >You Have rated this ${rate.rating}</p> --%>
<%-- 			  	</c:if> --%>
<%-- 			 </c:forEach> --%>
<%-- 			 user_id = ${user.id} show_id = ${show.id} --%>
<!--  ################################################################################### -->
<!-- 			<div class="d-flex flex-row"> -->
<%-- 				<form class="border" action="/rate/${show.id}" method="POST"> --%>
<!-- 					<p> -->
<!-- 						<label class="black label" path="rating">Rating: </label> -->
					
<!-- 						<select path="rating"> -->
<!-- 							<option value=1>1</option> -->
<!-- 							<option value=2>2</option> -->
<!-- 							<option value=3>3</option> -->
<!-- 							<option value=4>4</option> -->
<!-- 							<option value=5>5</option> -->
<!-- 						</select> -->
<!-- 					</p> -->
<%-- 					<input type="hidden" path="user" value="${user.id}"/> --%>
<%-- 					<input type="hidden" path="show" value="${show.id}"/> --%>
<!-- 					<input class="btns" type="submit" value="Rate"/> -->
<%-- 				</form> --%>
<!-- 		</div> -->
		

<!-- ################################################################################# Exam code below-->
<!-- 			<div class="d-flex flex-row"> -->
<%-- 				<form:form class="border" action="/rate/${show.id}" method="POST" modelAttribute="rating"> --%>
<!-- 					<p> -->
<%-- 						<form:label class="black label" path="rating">Rating: </form:label> --%>
<%-- 						<form:errors class="text-danger" path="rating"/> --%>
<%-- 						<form:select path="rating"> --%>
<!-- 							<option value=1>1</option> -->
<!-- 							<option value=2>2</option> -->
<!-- 							<option value=3>3</option> -->
<!-- 							<option value=4>4</option> -->
<!-- 							<option value=5>5</option> -->
<%-- 						</form:select> --%>
<!-- 					</p> -->
<%-- 					<form:hidden path="user" value="${user.id}"/> --%>
<%-- 					<form:hidden path="show" value="${show.id}"/> --%>
<!-- 					<input class="btns" type="submit" value="Rate"/> -->
<%-- 				</form:form> --%>
<!-- 		</div> -->
		
<!--  ################################################################################### -->
		
		
<!-- 		<div class="container mt-3 pb-3"> -->
<!-- 		  <h2>Users who liked this show: </h2>          -->
<!-- 		  <table class="table table-striped"> -->
<!-- 			<thead> -->
<!-- 			  <tr> -->
<!-- 			    <th>Name</th> -->
<!-- 			  </tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 				<c:forEach items="${show.likers}" var="liker"> --%>
<!-- 				  <tr> -->
<%-- 				    <td>${liker.firstName} ${liker.lastName}</td> --%>
<!-- 				  </tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</tbody> -->
<!-- 		  </table> -->

<!-- 		</div> -->

<%-- 	    	<c:choose> --%>
<%-- 	    	<c:when test="${show.likers.contains(user)}"> --%>
<%-- 	    		<a class="btns" href="/unlike/${show.id}">Unlike</a> --%>
<%-- 	    	</c:when> --%>
<%-- 	    	<c:otherwise> --%>
<%-- 	    		<a class="btns" href="/like/${show.id}">Like</a> --%>
<%-- 	    	</c:otherwise> --%>
<%-- 	    	</c:choose> --%>

<%-- <c:out value="${user.email}"></c:out> --%>