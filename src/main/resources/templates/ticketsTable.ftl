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
            <li><a href="/events/all">Events</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <table class="table">
        <caption>Tickets</caption>
        <tr>
            <th>Id</th>
            <th>Event</th>
            <th>Date</th>
            <th>Seats</th>
            <th>User</th>
            <th>Price</th>
        </tr>
    <#list tickets as ticket>
        <tr>
            <td>${ticket.id}</td>
            <td>${ticket.event.name}</td>
            <td>${ticket.dateTime}</td>
            <td>${ticket.seats}</td>
            <td>${ticket.user.name}</td>
            <td>${ticket.price}</td>
        </tr>
    </#list>
    </table>
</div>
</body>
</html>



