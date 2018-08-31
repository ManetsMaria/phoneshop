<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: masha
  Date: 22.8.18
  Time: 10.57
  To change this template use File | Settings | File Templates.
--%>
<c:import url="header.jsp"/>
<html>
<head>
    <title>Product Detail</title>
    <style>
        .sign {
            margin-left: 0;
        }
        .sign figcaption {
            width: 500px;
            margin-left: 0;
        }
        table{
            margin-bottom: 20px;
            margin-top: 20px;
        }
        .layer1 {
            float: left; /* Обтекание по правому краю */
            height: 500px;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/productList" ><button>return to product list</button></a>
<h2>${phone.model}</h2>
<div align="left" class="layer1">
    <figure class="sign">
        <p><img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}"></p>
        <figcaption>${phone.description}</figcaption>
    </figure>
    <b>Price: </b>${phone.price}
    <p><input type="text" id="quantity-form" width="10">
    <input type="button" onclick="doAjax('quantity-form', ${phone.id}, 'error')" value="flagAdd to cart"> </p>
    <input type="text" id="error" style="border:none" width="10" height="10">
</div>
<div align="center">
<table>
    <caption><b>Display</b></caption>
    <tbody>
    <tr>
        <td>Size </td>
        <td> ${phone.displaySizeInches}</td>
    </tr>
    <tr>
        <td>Pixel density </td>
        <td> ${phone.pixelDensity}</td>
    </tr>
    <tr>
        <td>Resolution </td>
        <td> ${phone.displayResolution}</td>
    </tr>
    <tr>
        <td>Technology </td>
        <td> ${phone.displayTechnology}</td>
    </tr>
    </tbody>
</table>
<table>
    <caption><b>Dimension & Weight</b></caption>
    <tbody>
    <tr>
        <td>Length </td>
        <td> ${phone.lengthMm}</td>
    </tr>
    <tr>
        <td>Width </td>
        <td> ${phone.widthMm}</td>
    </tr>
    <tr>
        <td>Weight </td>
        <td> ${phone.weightGr}</td>
    </tr>
    </tbody>
</table>
    <table>
        <caption><b>Camera</b></caption>
        <tbody>
        <tr>
            <td>Front </td>
            <td> ${phone.frontCameraMegapixels}</td>
        </tr>
        <tr>
            <td>Back </td>
            <td> ${phone.backCameraMegapixels}</td>
        </tr>
        </tbody>
    </table>
    <table>
        <caption><b>Battery </b></caption>
        <tbody>
        <tr>
            <td>Talk time </td>
            <td> ${phone.talkTimeHours}</td>
        </tr>
        <tr>
            <td>Stand by time </td>
            <td> ${phone.standByTimeHours}</td>
        </tr>
        <tr>
            <td>Battery capacity </td>
            <td> ${phone.batteryCapacityMah}</td>
        </tr>
        </tbody>
    </table>
    <table>
        <caption><b>Other </b></caption>
        <tbody>
        <tr>
            <td>Colors </td>
            <td> <c:forEach var="color" items="${phone.colors}">
                <c:out value="${color.code}; "/>
            </c:forEach></td>
        </tr>
        <tr>
            <td>Device type </td>
            <td> ${phone.deviceType}</td>
        </tr>
        <tr>
            <td>Bluetooth </td>
            <td> ${phone.bluetooth}</td>
        </tr>
        </tbody>
    </table>

</div>
</body>
</html>
