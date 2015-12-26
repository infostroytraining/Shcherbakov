<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
    <link href="css/style.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

    <div class="container">

        <div class="row">
            <form id="login-form" action="/login" method="post" class="form-horizontal">

                <div id="form-email" class="form-group">
                    <label class="col-sm-1 control-label" for="email" >email</label>
                    <div id="email" class="col-sm-4">
                        <input type="text"  name="email" class="form-control" value="${registrationBean.email}" placeholder="email" required/>
                    </div>
                </div>

                <div id="form-password" class="form-group ">
                    <label class="col-sm-1 control-label" for="password">Language</label>
                    <div id="password" class="col-sm-4">
                        <input type="text"  name="password" class="form-control" placeholder="password" required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-4">
                        <input class="btn btn-primary login" type="submit"  value="Login">
                    </div>
                </div>

            </form>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-4">
                    <a href="/registration"><input class="btn btn-primary register" type="submit"  value="Register"></a>
                </div>
            </div>

        </div>
    </div>
</body>
</html>
