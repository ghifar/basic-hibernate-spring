<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>List Customers</title>
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css"/>
					<!-- pageContext.request.contextPath for return the actual app name  -->
	
			
	
</head>

<body>
<h2>CRM- Customer Relationship Manager</h2>

<div id="container">
	<div id="content">
	<input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd';return false;" class="add-button"/>
	
	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Action</th>
		</tr>
		
		<c:forEach var="tempCustomer" items="${customers}">
		<!--  defined names "customers" called from the theModel.addAttribute("customers",theCustomers) from CustomerController class-->
		
		<!-- buat variabel updateLink dan deleteLink untuk dipanggil dibawah nanti -->
			<c:url var="updateLink" value="/customer/showFormForUpdate">
				<c:param name="customerId" value="${tempCustomer.id}"></c:param>
			</c:url>
			
			<c:url var="deleteLink" value="/customer/deleteCustomer"> 
				<c:param name="customerId" value="${tempCustomer.id}"></c:param>
			</c:url>
		
			<tr>
				<td>${tempCustomer.firstName}</td> <!-- remember. this is actually tempCustomer.getFirstName().  -->
				<td>${tempCustomer.lastName}</td><!-- same as above tempCustomer.getLastName() -->
				<td>${tempCustomer.email}</td>
				
				<!-- Panggil variabel updateLink dan deleteLink yang dibuat diatas  -->
				<td> <a href="${updateLink}">Update</a></td>
				<td> <a href="${deleteLink}" onclick="if (!(confirm('are you sure you want to delete this customer'))) return false">Delete </a>
				
			</tr>
		
		</c:forEach>
		
	</table>
	
	
	</div>
</div>

</body>


</html>