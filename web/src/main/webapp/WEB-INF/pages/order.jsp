<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: masha
  Date: 29.8.18
  Time: 12.49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<c:import url="header.jsp"/>
<h2>Order</h2>
<a href="${pageContext.request.contextPath}/cart" ><button>back to cart</button></a>
<form:form modelAttribute="order" method="post" id="order">
    <c:import url="orderTable.jsp"/>

   <div align="center"><form:errors path="outOfStock" cssClass="error" cssStyle="color: red"/></div>
    <p>
       first name*: <form:input path="firstName"/>
        <form:errors path="firstName" cssClass="error" cssStyle="color: red"/>
    </p>
    <p>
        last name*: <form:input path="lastName"/>
        <form:errors path="lastName" cssClass="error" cssStyle="color: red"/>
    </p>
    <p>
        phone*: <form:input path="contactPhoneNo"/>
        <form:errors path="contactPhoneNo" cssClass="error" cssStyle="color: red"/>
    </p>
    <p>
        address*: <form:input path="deliveryAddress"/>
        <form:errors path="deliveryAddress" cssClass="error" cssStyle="color: red"/>
    </p>
    <p>
        additional information:
    </p>
    <p>
        <form:input path="additionalInformation" style="width: 250px; height: 200px;"/>
    </p>
    <input type="submit" value="Order">
    <c:forEach var="orderItem" items="${order.orderItems}" varStatus="status">
    <form:hidden path="orderItems[${status.index}].phone.brand"/>
    <form:hidden path="orderItems[${status.index}].phone.model"/>
    <form:hidden path="orderItems[${status.index}].phone.price"/>
    <form:hidden path="orderItems[${status.index}].phone.displaySizeInches"/>
    <form:hidden path="orderItems[${status.index}].quantity"/>
    <form:hidden path="orderItems[${status.index}].subtotal"/>
    <form:hidden path="orderItems[${status.index}].phone.id"/>
    <form:hidden path="subtotal"/>
    <form:hidden path="deliveryPrice"/>
    <form:hidden path="totalPrice"/>
      <%--  <c:forEach var="color" items="${orderItem.phone.colors}" varStatus="statusColor">
            <form:hidden path="orderItems[${status.index}].phone.colors[${statusColor.index}].code"/>
        </c:forEach> --%>
    </c:forEach>
</form:form>
</body>
</html>
