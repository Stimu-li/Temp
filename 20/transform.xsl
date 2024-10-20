<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Sports List</title>
            </head>
            <body>
                <h1>Sports and Their Descriptions</h1>
                <table border="1">
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Players</th>
                        <th>Origin</th>
                    </tr>
                    <xsl:for-each select="sports/sport">
                        <tr>
                            <td><xsl:value-of select="@name"/></td>
                            <td><xsl:value-of select="description"/></td>
                            <td><xsl:value-of select="players"/></td>
                            <td><xsl:value-of select="origin"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
