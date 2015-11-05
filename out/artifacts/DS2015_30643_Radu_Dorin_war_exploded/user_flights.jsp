<%--
  Created by IntelliJ IDEA.
  User: radud
  Date: 28/10/2015
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<table border="1" width="100%" cellpadding="3">
    <thead>
    <th>Nr</th>
    <th>Flight nr.</th>
    <th>Airplane type</th>
    <th>Departure city</th>
    <th>Departure date</th>
    <th>Arrival city</th>
    <th>Arrival date</th>

    </thead>
    <tbody>
    <c:forEach items="${flights}" var="flight" varStatus="loop">
        <form id="flightData" action="flightsProcess" method="GET">
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
