<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h3>Welcome to ticket booking system</h3>
    <form action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="input-group">
            <input id="email" class="form-control" type="text" name="username" placeholder="Email"/>
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        </div>
        <div class="input-group">
            <input id="password" class="form-control" type="password" name="password" placeholder="Password"/>
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
        </div>
        <div class="input-group"><label> Remember Me: <input type="checkbox" name="remember-me"/> </label></div>
        <div class="input-group"><input type="submit" value="Sign In"/></div>
    </form>

<#if RequestParameters.error??>
    <div class="alert alert-warning">
        The email or password you have entered is invalid, try again.
    </div>
</#if>
    <br/>
    <p>You may use predefined test users:</p>
    <p>REGISTERED_USER [userEmail: user@booking.com, password: user123]</p>
    <p>BOOKING_MANAGER [userEmail: admin@booking.com, password: admin123]</p>
</div>
</body>
</html>