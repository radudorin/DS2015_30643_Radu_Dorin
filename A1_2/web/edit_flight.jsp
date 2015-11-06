<%--
  Created by IntelliJ IDEA.
  User: radud
  Date: 03/11/2015
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="/process" method="post">
    <div style="text-align: center;">
        <table border="1" width="30%" cellpadding="3">
            <thead>
            <tr>
                <th colspan="2">Create Flight</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Flight ID</td>
                <td><input type="text" name="flight_id" value=${flight_id} readonly></td>
            </tr>
            <tr>
                <td>Flight number</td>
                <td><input type="text" name="flight_nr" value=${flight_nr}></td>
            </tr>
            <tr>
                <td>Airplane type</td>
                <td><input type="text" name="airplane_type" value=${airplane_type}></td>
            </tr>
            <tr>
                <td>Departure city</td>
                <td><input type="text" name="departure_city" value=${departure_city}></td>
            </tr>
            <tr>
                <td>Departure date and hour</td>
                <td><input type="text" name="departure_date_and_hour" value=${departure_date_and_hour}></td>
            </tr>
            <tr>
                <td>Arrival city</td>
                <td><input type="text" name="arrival_city" value=${arrival_city}></td>
            </tr>
            <tr>
                <td>Arival date and hour</td>
                <td><input type="text" name="arrival_date_and_hour" value=${arrival_date_and_hour}></td>
            </tr>
            <tr>
                <td><input type="submit" value="Edit Flight" name="edit_flight"/></td>
                <td><input type="reset" value="Reset"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</form>

</body>
</html>
