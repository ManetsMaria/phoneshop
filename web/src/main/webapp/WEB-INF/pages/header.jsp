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
<p align="right"><a href="#">Login</a></p>
<p align="right"><a href="./cart" ><button>my cart: <span id="quantity">${cartService.cart.quantity}</span> item <span id="summa">${cartService.cart.summa}</span> $</button></a>
<h1>Phonify</h1>
<p align="right"></p>
</body>

<script type="text/javascript">
    function doAjax(idForm, id) {
        var inputText = document.getElementById(idForm).value;
        document.getElementById(idForm).value = '';
        $.ajax({
            url : 'ajaxCart',
            type: 'Post',
            data : ({
                phoneId: id,
                quantity: inputText
            }),

            success: function(data) {
                var array = data.split(';', 3);
                if (array[0] === 'true'){
                    $('#quantity').html(array[1]);
                    $('#summa').html(array[2]);
                }else{
                    document.getElementById(idForm).value = 'unsuitable data';
                }
            },
            error: function (data, e) {
                alert(data + e);
            }
        });
    }
</script>
</html>
