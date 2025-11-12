<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>People page</title>
            </head>
            <body>
                <h1>The Person page</h1>
                <table border="1">
                    <tr>
                        <th>Id</th>
                        <th>Full Name</th>
                        <th>Year of birth</th>
                        <th>Email</th>
                    </tr>
                    <xsl:for-each select="people/*">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="fullName"/></td>
                            <td><xsl:value-of select="yearOfBirth"/></td>
                            <td><xsl:value-of select="email"/></td>
                            <td>
                                <form method="GET" style="display: inline">
                                    <xsl:attribute name="action">/api/person/<xsl:value-of select="id"/>/get</xsl:attribute>
                                    <input type="submit" value="Edit"/>
                                </form>
                                <form style="display: inline">
                                    <xsl:attribute name="onsubmit">deletePerson(event, <xsl:value-of select="id"/>)</xsl:attribute>
                                    <input type="submit" value="Delete"/>
                                </form>
                                <form action="/api/book/get-all" method="GET" style="display: inline">
                                    <input type="hidden" name="person-id" value="{id}"/>
                                    <input type="submit" value="Books"/>
                                </form>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                <form action="/api/person/get-null" method="GET">
                    <input type="submit" value="Add person"/>
                </form>
                <form action="/api/book/get-all" method="GET">
                    <input type="submit" value="To books list"/>
                </form>
                <script src="/../js/person.js"/>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>