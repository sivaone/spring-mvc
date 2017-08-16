<#ftl strip_whitespace = true>

<#assign charset="UTF-8">
<#assign title="Booking Result">

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="${charset}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        Booking Result: <h3>${bookedTicket}</h3>

        <a href="<@spring.url '/logout.html' />">Logout</a>
    </body>
</html>
