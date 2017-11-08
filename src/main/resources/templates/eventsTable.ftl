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
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
                <li><a href="/users/all">Users</a></li>
                <li class="active"><a href="/events/all">Events</a></li>
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
        <caption>Events</caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Rate</th>
            <th>Price</th>
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

    <br/>
    <h3> Book ticket for event </h3>
    <form action="/ticket/book" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="input-group">
            <p>Event Id:</p>
            <input class="form-control" type="text" name="eventId" placeholder="Event Id"/>
        </div>
        <div class="input-group">
            <p>Seats:</p>
            <input class="form-control" type="text" name="seats" placeholder="16,67"/>
        </div>
        <div class="form-group">
            <input type="submit" value="Book ticket"/>
        </div>
    </form>

<#if isBookingManager?? && isBookingManager==true>
    <br/>
    <form action="/tickets" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="input-group">
            <input class="form-control" type="text" name="eventId" placeholder="Event Id"/>
        </div>
        <div class="form-group">
            <input type="submit" value="View all booked tickets for event"/>
        </div>
    </form>
</#if>
</div>
</body>
</html>
