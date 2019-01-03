<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: masha
  Date: 14.8.18
  Time: 16.08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="<c:url value="/resources/js/func.js" />"></script>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <title>Phonify</title>
</head>
<body>
<c:import url="/initCart"/>
<p align="right"><a href="${pageContext.request.contextPath}/admin/orders">Login</a></p>
<p align="right"><a href="${pageContext.request.contextPath}/cart" ><button>my cart: <span id="quantity"><c:out value = "${cart.quantity}"/></span> item <span id="summa"><c:out value = "${cart.summa}"/></span> $</button></a>
<h1>Phonify</h1>
<p align="right"></p>
</body>
</html>
