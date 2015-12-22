<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored='false' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Reg</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>


<form action="/registration" enctype="multipart/form-data" method="post">
    <div>
        <div>
            <span>First Name<label>*</label></span>
            <input type="text" name="name" value="${registrationBean.name}" required>
        </div>
        <div>
            <span>Last Name<label>*</label></span>
            <input type="text" name="last_name" value="${registrationBean.lastName}" required>
        </div>
        <div>
            <span>Email Address<label>*</label></span>
            <input type="text" name="email" value="${registrationBean.email}" required>
        </div>
    </div>
    <div>
        <div>
            <span>Password<label>*</label></span>
            <input type="password" name="password" required>
        </div>
        <div>
            <span>Confirm Password<label>*</label></span>
            <input type="password" name="repeated_password" required>
        </div>
        <div>
            <span>Avatar</span>
            <input type="file" name="avatar" accept="image/">
        </div>
        <div>
            <span>Captcha<label>*</label></span>
            <tf:getCaptcha></tf:getCaptcha>
        </div>
    </div>
    <input type="submit" value="submit">
</form>

<div>
    <c:forEach var="error" items="${requestScope.validationErrors}">
        <span>${error}</span>
    </c:forEach>
</div>
</body>
</html>