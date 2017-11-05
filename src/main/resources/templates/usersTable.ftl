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
            <li><a href="/home">Home</a></li>
            <li class="active"><a href="/users/all">Users</a></li>
            <li><a href="/events/all">Events</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <table class="table">
        <caption>Users</caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Date of birth</th>
        </tr>
    <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.birthday}</td>
        </tr>
    </#list>
    </table>
</div>
</body>
</html>
