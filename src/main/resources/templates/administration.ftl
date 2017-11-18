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
                <li class="active"><a href="/administration">Administration</a></li>
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
    <h3>Multiple users upload from json file</h3>
    <form action= "/users/upload" enctype="multipart/form-data" method="post">
        Select json files: <input type="file" name="files" multiple>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Upload"/>
    </form>
    <h3>Multiple events upload from json file</h3>
    <form action= "/events/upload" enctype="multipart/form-data" method="post">
        Select json files: <input type="file" name="files" multiple>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Upload"/>
    </form>
</div>
</body>
</html>