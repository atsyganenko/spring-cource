<!DOCTYPE html>
<html lang="en">

<body>
<form action= ${formAction} enctype="multipart/form-data" method="post">
    Select files: <input type="file" name="files" multiple>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Upload"/>
</form>
</body>

</html>
