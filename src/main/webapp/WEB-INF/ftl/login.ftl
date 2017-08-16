<#ftl strip_whitespace = true>

<#import "/spring.ftl" as spring />

<#assign charset="UTF-8">
<#assign title="Login page">

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="${charset}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        </head>
    <body>
        <form action="<@spring.url '/j_spring_security_check' />" method="post">
    <label for="username">User Id</label><input type="text" id="username" name="username"><br/>
    <label for="password">Password</label><input type="password" id="password" name="password"><br/>
    <input type="submit" value="Login">
    </form>

    </body>
</html>
