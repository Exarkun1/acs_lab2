<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Book form</title>
            </head>
            <body>
                <h1>The Book form</h1>
                <form onsubmit="updateBook(event)">
                    <input id="id" type="hidden" value="{book/id}"/>
                    <label for="title">Title:</label>
                    <input id="title" type="text" value="{book/title}"/><br/>
                    <label for="author">Author:</label>
                    <input id="author" type="text" value="{book/author}"/><br/>
                    <label for="person_id">Person id:</label>
                    <input id="person_id" type="text" value="{book/personId}"/><br/>
                    <input type="submit" value="Save"/>
                </form>
                <form action="/api/book/get-all" method="GET">
                    <input type="submit" value="Back to books list"/>
                </form>
                <script src="/../js/book.js"/>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>