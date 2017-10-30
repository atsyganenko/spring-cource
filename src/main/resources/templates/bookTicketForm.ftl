<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring />
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Book ticket</h2>
    <form action="book/confirm" method="post" name="ticket">
        <div class="form-group">
            <label>Event name:</label>
        <@spring.formInput"ticket.event.name"/>
        </div>
        <div class="form-group">
            <label>Seats: </label>
        <@spring.formInput"ticket.seats"/>
        </div>
    <@spring.formHiddenInput "ticket.user.email"/>
    <@spring.formHiddenInput "ticket.event.id"/>
        <div class="form-group">
            <input type="submit" value="Confirm"/>
        </div>
    </form>
</div>
</body>

</html>
