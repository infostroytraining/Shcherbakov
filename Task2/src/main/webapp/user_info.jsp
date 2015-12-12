<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User Info</title>
</head>
<body>

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
        ${user.name}
      </td>
      <td>
        ${user.lastName}
      </td>
      <td>
        ${user.email}
      </td>
      <td>
        ${user.password}
      </td>
      <td>
        <img src="/uploadAvatar" width="50" height="50">
      </td>
    </tr>

  </table>
</div>

</body>
</html>
