<%--
  Created by IntelliJ IDEA.
  User: radud
  Date: 27/10/2015
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/login">
    <form method="post" action="/login">
        <div style="text-align: center;">
            <table border="1" width="30%" cellpadding="3">
                <thead>
                <tr>
                    <th colspan="2">Login Here</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>User Name</td>
                    <td><input type="text" name="username" value=""/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value=""/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"/></td>
                    <td><input type="reset" value="Reset"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</form>
</body>
</html>
