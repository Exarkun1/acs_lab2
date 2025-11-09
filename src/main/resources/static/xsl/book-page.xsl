<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Books page</title>
            </head>
            <body>
                <h1>The Books page</h1>
                <table border="1">
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Status</th>
                    </tr>
                    <xsl:for-each select="books/*">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="title"/></td>
                            <td><xsl:value-of select="author"/></td>
                            <td><xsl:value-of select="status"/></td>
                            <td>
                                <form method="GET" style="display: inline">
                                    <xsl:attribute name="action">/api/book/<xsl:value-of select="id"/>/get</xsl:attribute>
                                    <input type="submit" value="Edit"/>
                                </form>
                                <form style="display: inline">
                                    <xsl:attribute name="onsubmit">deleteBook(event, <xsl:value-of select="id"/>)</xsl:attribute>
                                    <input type="submit" value="Delete"/>
                                </form>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                <form action="/api/book/get-null" method="GET">
                    <input type="submit" value="Add book"/>
                </form>
                <script src="/../js/book.js"/>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>