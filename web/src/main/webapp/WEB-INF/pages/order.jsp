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
<c:import url="orderTable.jsp"/>
<form:form modelAttribute="orderForm" method="post" id="orderForm">
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
        phone*: <form:input path="phone"/>
        <form:errors path="phone" cssClass="error" cssStyle="color: red"/>
    </p>
    <p>
        address*: <form:input path="address"/>
        <form:errors path="address" cssClass="error" cssStyle="color: red"/>
    </p>
    <p>
        additional information:
    </p>
    <p>
        <form:input path="additionalInformation" style="width: 250px; height: 200px;"/>
    </p>
    <input type="submit" value="Order">
</form:form>
</body>
</html>
