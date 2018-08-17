<%--
  Created by IntelliJ IDEA.
  User: masha
  Date: 14.8.18
  Time: 16.08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="cart" scope="session" class="com.es.core.cart.Cart" />
<html>
<head>
    <style>
        h1 {
            font-size: 200%;
            border-bottom: 2px solid maroon;
            font-weight: normal;
            padding-bottom: 5px;
        }
    </style>
    <script type="text/javascript">
        function doAjax(inputText, id) {
            $.ajax({
                url : 'ajaxCart',
                type: 'Post',
                data : ({
                    phoneId: id,
                    quantity: inputText
                }),

                success: function() {

                    alert("ok");
                }
            });
        }
    </script>
    <title>Phonify</title>
</head>
<body>
<p align="right"><a href="#">Login</a>
<p align="right">my cart: ${cart.quantity} item ${cart.summa} $</p>
</p>
<h1>Phonify</h1>
<p align="right"></p>
</body>
</html>
