<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <table class="table">
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