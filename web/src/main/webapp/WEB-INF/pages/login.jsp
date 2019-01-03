<%--
  Created by IntelliJ IDEA.
  User: manet
  Date: 07.09.2018
  Time: 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  ${loginError}
<form name='loginForm' action="login" method='post'>
Username
    <input id="user" type='text' name='username'>
    <p>
    Password
    <input id="pass" type='password' name='password' />
    <p>
    <input name="submit" type="submit" value="Submit" />
</form>
