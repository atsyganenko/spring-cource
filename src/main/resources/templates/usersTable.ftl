<!DOCTYPE html>
<html lang="en">
<body>
<div>
    <table>
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
