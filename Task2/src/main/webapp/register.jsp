<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored='false' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Reg</title>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
    <script src="http://cdn.jsdelivr.net/jquery.validation/1.14.0/jquery.validate.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
 <!--   <script src=" http://code.jquery.com/jquery-2.1.4.min.js"></script> -->

    <script type="text/javascript" src="js/jquery-validation.js"></script>


    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


</head>
<body>
<div class="panel-body">
<div class="container">

    <div class="row">

<form id="registration_form" action="/registration" enctype="multipart/form-data" method="post"  class="form-horizontal">
    <div id="form-firstname" class="form-group">
        <label class="col-sm-1 control-label" for="firstname" >First name</label>
        <div id="firstname" class="col-sm-4">
            <input type="text"  name="name" class="form-control" value="${registrationBean.name}"  required/>
        </div>
    </div>
    <div id="form-lastname" class="form-group">
        <label class="col-sm-1 control-label" for="lastname" >Last name</label>
        <div id="lastname" class="col-sm-4">
            <input type="text"  name="last_name" class="form-control" value="${registrationBean.lastName}"  required/>
        </div>
    </div>
    <div id="form-email" class="form-group">
        <label class="col-sm-1 control-label" for="email" >Email</label>
        <div id="email" class="col-sm-4">
            <input type="text"  name="email" class="form-control" value="${registrationBean.email}"  required/>
        </div>
    </div>
    <div id="form-password" class="form-group">
        <label class="col-sm-1 control-label" for="password" >Password</label>
        <div id="password" class="col-sm-4">
            <input type="text"  name="password" class="form-control" required/>
        </div>
    </div>
    <div id="form-repeatedpassword" class="form-group">
        <label class="col-sm-1 control-label" for="repeated_password" >Repeated password</label>
        <div id="repeated_password" class="col-sm-4">
            <input type="text"  name="repeated_password" class="form-control" required/>
        </div>
    </div>
    <div id="form-avatar" class="form-group">
         <label class="col-sm-1 control-label" for="avatar" >Avatar</label>
         <div id="avatar" class="col-sm-4">
              <input type="file"  name="avatar" accept="image/" class="form-control" />
         </div>
    </div>
        <div>
            <span>Captcha<label>*</label></span>
            <tf:getCaptcha></tf:getCaptcha>
        </div>
    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-4">
            <input class="btn btn-primary register" type="submit"  value="Register">
        </div>
    </div>

</form>

        </div>
    </div>
</div>

</body>
</html>