<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User Info</title>
</head>
<body>
<form action="/logout" method="post">
  <input type="submit" value="Logout" />
</form>
<div>
  <table>
    <tr>
      <td>Name</td>
      <td>Lastname</td>
      <td>Email</td>
      <td>Password</td>
      <td>Avatar</td>
    </tr>

    <tr>
      <td>
        ${sessionScope.currentUser.name}
      </td>
      <td>
        ${sessionScope.currentUser.lastName}
      </td>
      <td>
        ${sessionScope.currentUser.email}
      </td>
      <td>
        ${sessionScope.currentUser.password}
      </td>
      <td>
        <img src="/uploadAvatar" width="50" height="50">
      </td>
    </tr>

  </table>
</div>

</body>
</html>
