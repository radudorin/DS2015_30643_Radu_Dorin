<%--
  Created by IntelliJ IDEA.
  User: radud
  Date: 13/12/2015
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Produce</title>
</head>
<body>

<form action="/produce" method="post">
    <div style="text-align: center;">
        <table border="1" width="30%" cellpadding="3">
            <thead>
            <tr>
                <th colspan="2">Create Flight</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>DVD title</td>
                <td><input type="text" name="dvd_title" value=""/></td>
            </tr>

            <tr>
                <td>DVD price</td>
                <td><input type="text" name="dvd_price" value=""/></td>
            </tr>

            <tr>
                <td>DVD year</td>
                <td><input type="text" name="dvd_year" value=""/></td>
            </tr>

            <tr>
                <td><input type="submit" value="Post to queue" name="post_to_queue"/></td>
                <td><input type="reset" value="Reset"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</form>

</body>
</html>
