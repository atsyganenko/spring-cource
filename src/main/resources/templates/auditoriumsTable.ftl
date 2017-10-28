<!DOCTYPE html>
<html lang="en">
<body>
<div>
    <table>
        <caption>Auditoriums</caption>
        <tr>
            <th>Name</th>
            <th>Seats Number</th>
            <th>Vip Seats</th>
        </tr>
    <#list auditoriums as auditorium>
        <tr>
            <td>${auditorium.name}</td>
            <td>${auditorium.seatsNumber}</td>
            <td>${auditorium.vipSeats}</td>
        </tr>
    </#list>
    </table>
</div>
</body>
</html>