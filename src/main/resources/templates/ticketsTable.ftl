<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/home">Booking</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/home">Home</a></li>
                <li><a href="/users/all">Users</a></li>
                <li><a href="/events/all">Events</a></li>
            <#if isBookingManager?? && isBookingManager==true>
                <li><a href="/administration">Administration</a></li>
            </#if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-link btn-logout">Logout</button>
                    </form>
                </li>
            </ul>
        </div>
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



