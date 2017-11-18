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
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-6">
            <h3>Create single user</h3>
            <form action="/users/add" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="input-group">
                    <label for="name">Name:</label for="name">
                    <input class="form-control" type="text" id="name" name="name" placeholder="User Name"/>
                </div>
                <div class="input-group">
                    <label for="email">Email:</label for="email">
                    <input class="form-control" type="text" id="email" name="email" placeholder="User Email"/>
                </div>
                <div class="input-group">
                    <label for="password">Password:</label for="password">
                    <input class="form-control" type="password" id="password" name="password"
                           placeholder="New Password"/>
                </div>
                <label>Booking manager: <input type="checkbox" name="isBookingManager"/> </label>
                <div class="input-group">
                    <label for="date">Date of birth:</label>
                    <input class="form-control" type="date" id="date" name="birthday" placeholder="Select Date"/>
                </div>
                <div class="form-group">
                    <input type="submit" value="Add User"/>
                </div>
            </form>
        </div>
        <div class="col-sm-6">
            <h3>Create single event</h3>
            <form name="event" action="/events/add" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="input-group">
                    <label for="name">Name:</label>
                    <input class="form-control" type="text" id="name" name="name" placeholder="Event Name"/>
                </div>
                <div class="input-group">
                    <label for="price">Base Price:</label>
                    <input class="form-control" type="text" id="price" name="basePrice" placeholder="0.00"/>
                </div>
                <div class="input-group">
                    <label for="rate">Rate:</label>
                    <select class="form-control" size="1" id="rate" name="rate">
                        <option value="HIGH">HIGH</option>
                        <option selected value="MID">MID</option>
                        <option value="LOW">LOW</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="auditorium">Auditorium Name:</label>
                    <input class="form-control" type="text" id="auditorium" name="auditorium.name"
                           placeholder="Blue hall"/>
                </div>
                <div class="input-group">
                    <label for="date">Date:</label>
                    <input class="form-control" type="datetime-local" id="date" name="dateTime"
                           placeholder="Select Date"/>
                </div>
                <div class="form-group">
                    <input type="submit" value="Add Event"/>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <h3>Multiple users upload from json file</h3>
            <form action="/users/upload" enctype="multipart/form-data" method="post">
                Select json files: <input type="file" name="files" multiple>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Upload"/>
            </form>
        </div>
        <div class="col-sm-6">
            <h3>Multiple events upload from json file</h3>
            <form action="/events/upload" enctype="multipart/form-data" method="post">
                Select json files: <input type="file" name="files" multiple>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Upload"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>