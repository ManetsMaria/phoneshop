<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: masha
  Date: 31.8.18
  Time: 13.48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<table border="1px" align="center">
    <thead>
    <tr>
        <td>Brand </td>
        <td>Model </td>
        <td>Price </td>
        <td>Colors</td>
        <td>Display size</td>
        <td>Quantity</td>
        <td>Price</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="orderItem" items="${order.orderItems}">
    <tr>
        <td>${orderItem.phone.brand}</td>
        <td>${orderItem.phone.model}</td>
        <td>$ ${orderItem.phone.price}</td>
        <td><c:forEach var="color" items="${orderItem.phone.colors}">
            <c:out value="${color.code}; "/>
        </c:forEach></td>
        <td>${orderItem.phone.displaySizeInches}"</td>
        <td>${orderItem.quantity}</td>
        <td>${orderItem.subtotal}</td>
        </c:forEach>
    </tbody>
</table>
<table align="center">
    <tbody>
    <tr>
        <td>Subtotal</td>
        <td>${order.subtotal}</td>
    </tr>
    <tr>
        <td>Delivery</td>
        <td>${order.deliveryPrice}</td>
    </tr>
    <tr>
        <td>Total</td>
        <td>${order.totalPrice}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
