<%@ page import="validation.cart.CartForm" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: masha
  Date: 22.8.18
  Time: 14.10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <style>
        input {
            text-align: right;
        }
    </style>
</head>
<body>
<c:import url="header.jsp"/>
<h2>Cart</h2>
<a href="${pageContext.request.contextPath}/productList" ><button>return to product list</button></a>
<div align="right"><a href="${pageContext.request.contextPath}/order"><button >Order</button></a></div>
<form:form method="post" modelAttribute="cartForm" id="cartForm">
    <table border="1px" align="center">
        <thead>
        <tr>
            <td>Brand </td>
            <td>Model </td>
            <td>Price </td>
            <td>Colors</td>
            <td>Display size</td>
            <td>Quantity</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="phone" items="${phones}" varStatus="status">
        <tr>
            <td>${phone.brand}</td>
            <td>${phone.model}</td>
            <td>$ ${phone.price}</td>
            <td><c:forEach var="color" items="${phone.colors}">
                <c:out value="${color.code}; "/>
            </c:forEach></td>
            <td>${phone.displaySizeInches}"</td>
            <td>
                <form:input path="cartFormItems[${status.index}].quantity"/>
                <form:errors path="cartFormItems[${status.index}].quantity" cssClass="error"/>
                <form:hidden path="cartFormItems[${status.index}].phoneId"/>
            </td>
            <td>
                <button  onclick="deleteCart(${phone.id})">Delete</button>
            </td>
            </c:forEach>
        </tbody>
    </table>
</form:form>
<div align="right">
<button  onclick="updateCart()">Update</button>
    <a href="${pageContext.request.contextPath}/order"><button >Order</button></a></div>
<form method="post" action="${pageContext.request.contextPath}/cart/delete" id="deleteForm" name="deleteForm">
    <input type="hidden" name="id" id="id">
</form>
</body>
<script>var updateCart = function() {
    $('#cartForm').submit();
};
var deleteCart = function(id) {
    document.getElementById('id').value = id;
    $('#deleteForm').submit();
};
</script>
</html>