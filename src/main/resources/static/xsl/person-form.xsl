<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Person form</title>
            </head>
            <body>
                <h1>The Person form</h1>
                <form onsubmit="updatePerson(event)">
                    <input id="id" type="hidden" value="{person/id}"/>
                    <label for="full_name">Full name:</label>
                    <input id="full_name" type="text" value="{person/fullName}"/><br/>
                    <label for="year_of_birth">Year of birth:</label>
                    <input id="year_of_birth" type="text" value="{person/yearOfBirth}"/><br/>
                    <label for="email">Email:</label>
                    <input id="email" type="text" value="{person/email}"/><br/>
                    <input type="submit" value="Save"/>
                </form>
                <form action="/api/person/get-all" method="GET">
                    <input type="submit" value="Back to people list"/>
                </form>
                <script src="/../js/person.js"/>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>