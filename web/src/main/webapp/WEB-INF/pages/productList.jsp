<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- <script type="text/javascript" src="/path/to/jquery-latest.js"></script>
<script type="text/javascript" src="/path/to/jquery.tablesorter.js"></script>
<p>
  <script type="text/javascript">
      $(document).ready(function() {
          $("#phoneTable").tablesorter();
      });
  </script> -->

<h2>Phones</h2>
<p align="right"><input type="text" id="search"></p>
  <table border="1px" align="center" id="phoneTable" class="tablesorter">
    <thead>
      <tr>
        <td>Image</td>
        <td>Brand</td>
        <td>Model</td>
        <td>Price</td>
        <td>Colors</td>
        <td>Display size</td>
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
        <td>${phone.model}</td>
        <td>$ ${phone.price}</td>
        <td><c:forEach var="color" items="${phone.colors}">
           <c:out value="${color.code}; "></c:out>
        </c:forEach></td>
        <td>${phone.displaySizeInches}"</td>
        <td><input type="text" id="quantity-form"></td>
        <td><input type="button" onclick="doAjax(6, 7789)" value="add to cart"> </td>
      </tr>

    </c:forEach>
  </tbody>
  </table>
<p>
<div class="nav" align="center">
    <a href="?page=${currentPage - 1}"><input type="button" <c:if test="${currentPage == 1}"><c:out value="disabled='disabled'"/></c:if> value="previous"></a>
    ${currentPage}
    <a href="?page=${currentPage + 1}"><input type="button" <c:if test="${currentPage == pageCount}"><c:out value="disabled='disabled'"/></c:if> value="next"></a>
</div>