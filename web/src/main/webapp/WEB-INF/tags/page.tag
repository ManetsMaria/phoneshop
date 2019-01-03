<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<jsp:useBean id="cart" scope="session" class="com.es.core.cart.Cart" />
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="<c:url value="/resources/js/func.js" />"></script>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
                            <c:choose>
                                <c:when test="${pageContext.request.userPrincipal.name != null}">
                                    <p align="right"><a  href="<c:url value="/logout" />">
                                        <u>Logout</u>
                                    </a></p>

                                </c:when>
                                <c:otherwise>
<c:import url="/initCart"/>
<p align="right"><a href="<c:url value="/login"/>">
    <u>Login</u>
</a><p align="right"><a href="${pageContext.request.contextPath}/cart" ><button>my cart: <span id="quantity"><c:out value = "${cart.quantity}"/></span> item <span id="summa"><c:out value = "${cart.summa}"/></span> $</button></a>
                                    </p>
                                </c:otherwise>
                            </c:choose>

                            <h1>Phonify</h1>
        <jsp:doBody/>
<p align="center">footer</p>
</body>
</html>