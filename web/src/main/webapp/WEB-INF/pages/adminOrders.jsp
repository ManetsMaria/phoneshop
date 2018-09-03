<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: masha
  Date: 3.9.18
  Time: 9.07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<c:import url="adminHeader.jsp"/>
<h2>Orders</h2>
    <table border="1px" align="center">
        <thead>
        <tr>
            <td>Order number</td>
            <td>Customer </td>
            <td>Phone</td>
            <td>Address</td>
            <td>Total price</td>
            <td>Status</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
        <tr>
            <td><a href="${pageContext.request.contextPath}/admin/orderOverview?orderId=${order.id}">${order.id}</a></td>
            <td>${order.lastName.concat(" ").concat(order.firstName)}</td>
            <td>${order.contactPhoneNo}</td>
            <td>${order.deliveryAddress}"</td>
            <td>$ ${order.totalPrice}</td>
            <td>${order.status}</td>
        </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
