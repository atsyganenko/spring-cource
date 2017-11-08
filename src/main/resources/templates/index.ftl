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
                <li class="active"><a href="/home">Home</a></li>
                <li><a href="/users/all">Users</a></li>
                <li><a href="/events/all">Events</a></li>
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
    <h3>Hi ${user.name}:)</h3>
    <h3>Welcome to tickets booking system</h3>
    Available balance: ${user.account.balance}
    <form action="/balance/topUp" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="input-group">
            <input class="form-control" type="text" name="amount" placeholder="Amount"/>
        </div>
        <div class="form-group">
            <input type="submit" value="Top up account"/>
        </div>
    </form>
</div>
</body>
</html>