<!DOCTYPE html>
<html lang="en">
<body>
<div>
    <table>
        <caption>Users</caption>
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
