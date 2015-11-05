<%--
  Created by IntelliJ IDEA.
  User: radud
  Date: 02/11/2015
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/process" method="GET">
    <div style="text-align: left;">
        <input type="submit" name="flight_action" value="Create"/>
    </div>
</form>

<table border="1" width="100%" cellpadding="3">
    <thead>
    <th>Nr</th>
    <th>Flight nr.</th>
    <th>Airplane type</th>
    <th>Departure city</th>
    <th>Departure date</th>
    <th>Arrival city</th>
    <th>Arrival date</th>
    <th>Edit flight</th>
    <th>Delete flight</th>
    </thead>
    <tbody>
    <c:forEach items="${flights}" var="flight" varStatus="loop">
        <form id="flightData" action="/process" method="GET">
            <tr>
                <th>${loop.index+1}</th>
                <input type="hidden" id="flight${flight.id}" name="flightId" value="${flight.id}">
                <td><c:out value="${flight.flightNr}"/></td>
                <td><c:out value="${flight.airplaneType}"/></td>
                <td>
                    <c:out value="${flight.departureCity.latitude}"/>
                    <c:out value="${flight.departureCity.longitude}"/>
                </td>
                <td><c:out value="${flight.departureDateAndHour}"/></td>
                <td>
                    <c:out value="${flight.arrivalCity.latitude}"/>
                    <c:out value="${flight.arrivalCity.longitude}"/>
                </td>
                <td><c:out value="${flight.arrivalDateAndHour}"/></td>
                <td>
                    <input type="submit" value="Edit" class="btn btn-info" name="flight_action">
                </td>
                <td>
                    <input id="DeleteBtn" type="submit" value="Delete" name="flight_action" class="btn btn-danger">
                </td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>

<br>

<form action="/user">
    <div style="text-align: right;">
        <input type="submit" name="Logout" value="Logout"/>
    </div>
</form>

</body>
</html>
