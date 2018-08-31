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
    <title>Order overview</title>
</head>
<body>
<c:import url="header.jsp"/>
<h2>Thank you for your order</h2>
<h3>Order number: ${order.id}</h3>
<c:import url="orderTable.jsp"/>
<p>
    First name: ${orderForm.firstName}
</p>
<p>
    Last name: ${orderForm.lastName}
</p>
<p>
    Address: ${orderForm.address}
</p>
<p>
    Phone: ${orderForm.phone}
</p>
<p>
    ${orderForm.additionalInformation}
</p>
<a href="${pageContext.request.contextPath}/productList" ><button>back to shopping</button></a>
</body>
</html>
