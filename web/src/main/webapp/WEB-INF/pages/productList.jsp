<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="header.jsp"/>
<jsp:useBean id="converter" class="com.es.phoneshop.web.controller.pages.service.SpecificationConverterService" />

<h2>Phones</h2>
<style>
    table{
        margin-top: 10px;
    }
    input {
        text-align: right;
    }
</style>
<div align="right" >
    <form action="?" method="get" >
        <input type="text" name="search" value="${search}">
        <input type="hidden" value="1" name="page">
        <input type="submit" value="search">
    </form>
</div>
<table border="1px" align="center" id="phoneTable">
    <thead>
      <tr>
        <td>Image</td>
          <td>Brand <a href="?page=1&orderId=${converter.BRAND_INC}">&#8593;</a> <a href="?page=1&orderId=${converter.BRAND_DEC}">&#8595;</a></td>
        <td>Model <a href="?page=1&orderId=${converter.MODEL_INC}">&#8593;</a> <a href="?page=1&orderId=${converter.MODEL_DEC}">&#8595;</a></td>
        <td>Price <a href="?page=1&orderId=${converter.PRICE_INC}">&#8593;</a> <a href="?page=1&orderId=${converter.PRICE_DEC}">&#8595;</a></td>
        <td>Colors</td>
        <td>Display size <a href="?page=1&orderId=${converter.SCREEN_INC}">&#8593;</a> <a href="?page=1&orderId=${converter.SCREEN_DEC}">&#8595;</a></td>
        <td>Quantity</td>
        <td>Action</td>
      </tr>
    </thead>
  <tbody>
    <c:forEach var="phone" items="${phones}">
      <tr>
        <td>
          <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
        </td>
        <td>${phone.brand}</td>
          <td><a href="${pageContext.request.contextPath}/productDetails?phoneId=${phone.id}">${phone.model}</a></td>
        <td>$ ${phone.price}</td>
        <td><c:forEach var="color" items="${phone.colors}">
           <c:out value="${color.code}; "/>
        </c:forEach></td>
        <td>${phone.displaySizeInches}"</td>
      <td><input type="text" id="quantity-form${phone.id}" value="1"> <p>
          <input type="text" id="mistake${phone.id}" style="border:none" width="10" height="10"> </p></td>
        <td><input type="button" onclick="doAjax('quantity-form${phone.id}', ${phone.id}, 'mistake${phone.id}')" value="add to cart"> </td>
        <%--  <form:form id="quantityForm" name="quantityForm" modelAttribute="cartFormItem" method="post" onsubmit="doAjax()">
          <td><form:input path="quantity"/>
              <%-- <form:errors path="this" element="div" cssClass="error"/>
              <form:hidden path="phoneId"/></td>
          <td><button type="submit">flagAdd</button></td>
          </form:form> --%>
    </c:forEach>
  </tbody>
  </table>
<p>
<div class="nav" align="center">
    <a href="?page=${currentPage - 1}"><input type="button" <c:if test="${currentPage == 1}"><c:out value="disabled='disabled'"/></c:if> value="previous"></a>
    ${currentPage}/${pageCount}
    <a href="?page=${currentPage + 1}"><input type="button" <c:if test="${currentPage == pageCount}"><c:out value="disabled='disabled'"/></c:if> value="next"></a>
</div>
