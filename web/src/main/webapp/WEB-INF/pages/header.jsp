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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <style>
        h1 {
            font-size: 200%;
            border-bottom: 2px solid maroon;
            font-weight: normal;
            padding-bottom: 5px;
        }
    </style>
    <title>Phonify</title>
</head>
<body>
<p align="right"><a href="${pageContext.request.contextPath}/admin/orders">Login</a></p>
<p align="right"><a href="${pageContext.request.contextPath}/cart" ><button>my cart: <span id="quantity">${cartService.cart.quantity}</span> item <span id="summa">${cartService.cart.summa}</span> $</button></a>
<h1>Phonify</h1>
<p align="right"></p>
</body>

<script type="text/javascript">
    function doAjax(idForm, id, idMistake) {
        var inputText = document.getElementById(idForm).value;
        document.getElementById(idMistake).value = '';
        <!-- document.getElementById(idForm).value = ''; -->
        $.ajax({
            url : 'ajaxCart',
            type: 'Post',
            data : ({
                phoneId: id,
                quantity: inputText
            }),

            success: function(data) {
                if (data.errorMessage === null){
                    $('#quantity').html(data.quantity);
                    $('#summa').html(data.summa);
                }else{
                    document.getElementById(idMistake).value = data.errorMessage;
                }
            },
            error: function (data, e) {
                <%-- document.getElementById(idMistake).value = 'empty input'; --%>
            }
        });
    }
</script>
</html>
