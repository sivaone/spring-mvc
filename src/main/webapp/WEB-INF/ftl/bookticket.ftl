<#ftl strip_whitespace = true>

<#import "/spring.ftl" as spring />

<#assign charset="UTF-8">
<#assign title="Book ticket">

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="${charset}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        </head>
    <body>
        <div>

            <form action="<@spring.url '/booking/bookticket.html' />" method="post">
                <label for="userId">User Id</label><input type="text" id="userId" name="userId"><br/>
                <label for="eventId">Event Id</label><input type="text" id="eventId" name="eventId"><br/>
                <label for="seats">Seats</label><input type="text" id="seats" name="seats"><br/>
                <input type="submit" value="Book">
                </form>

            </div>

        </body>
    </html>
