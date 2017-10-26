<!DOCTYPE html>
<#import "/spring.ftl" as spring/>
<html lang="en">

<body>
<form action="/users/upload" enctype="multipart/form-data" method="post">
Select files: <input type="file"  name="files" multiple>
<input type="submit" value="Upload"/>
</form>
</body>

</html>
