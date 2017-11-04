<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Booking</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="#">Home</a></li>
            <li><a href="/users/all">Users</a></li>
            <li class="active"><a href="/events/all">Events</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <table class="table">
        <caption>Events</caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Rate</th>
            <th>Prica</th>
            <th>Date</th>
            <th>Auditorium</th>
        </tr>
    <#list events as event>
        <tr>
            <td>${event.id}</td>
            <td>${event.name}</td>
            <td>${event.rate}</td>
            <td>${event.basePrice}</td>
            <td>${event.dateTime}</td>
            <td>${event.auditorium.name}</td>
        </tr>
    </#list>
    </table>
</div>
</body>
</html>
