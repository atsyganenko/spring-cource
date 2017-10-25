<!DOCTYPE html>
<#import "/spring.ftl" as spring/>
<html lang="en">

<body>
<form action=<@spring.url relativeUrl="/users/save"/>, method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td>  <@spring.formInput "user.name" /> </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Save User" />
            </td>
        </tr>
    </table>
</form>
</body>

</html>
