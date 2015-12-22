<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post" name="login" id="login-form">
<div>
    <div>
        <span>Email Address<label>*</label></span>
        <input type="text" name="email" value="${registrationBean.email}" required>
    </div>
    <c:if test="${not empty wrongEmail}">
        ${wrongEmail}
    </c:if>
    <div>
        <span>Password<label>*</label></span>
        <input type="password" name="password" required>
    </div>
    <c:if test="${not empty wrongPassword}">
        ${wrongPassword}
    </c:if>
</div>
    <input type="submit" value="Login">
</form>
<div class="button1">
    <a href="/registration"><input type="submit" name="Submit" value="Create an Account"></a>
</div>

</body>
</html>
