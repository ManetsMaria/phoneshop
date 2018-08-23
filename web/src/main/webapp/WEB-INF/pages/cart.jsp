<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<h1>Cart</h1>
<a href="./productList" ><button>return to product list</button></a>
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
    <c:forEach var="phone" items="${phones}">
    <tr>
        <td>${phone.brand}</td>
        <td>${phone.model}</td>
        <td>$ ${phone.price}</td>
        <td><c:forEach var="color" items="${phone.colors}">
            <c:out value="${color.code}; "/>
        </c:forEach></td>
        <td>${phone.displaySizeInches}"</td>
        <td><input type="text" id="quantity-form${phone.id}" value="${cartService.cart.getPhoneQuantity(phone.id)}"></td>
        <td><input type="button" onclick="" value="delete"> </td>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
