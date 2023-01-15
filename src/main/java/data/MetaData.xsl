<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="application_number">
                <xsl:value-of select="//@applicationNumber" />
            </xsl:variable>
            <xsl:variable name="receipt_date">
                <xsl:value-of select="//@receiptDate" />
            </xsl:variable>
            <xsl:variable name="patent_name">
                <xsl:value-of select="//patentName" />
            </xsl:variable>
            <xsl:variable name="patent_name">
                <xsl:value-of select="//patentName" />
            </xsl:variable>
            <xsl:variable name="applicant">
                <xsl:value-of select="//applicant" />
            </xsl:variable>
            <rdf:Description rdf:about="{$application_number}">

                <application_number>
                    <xsl:value-of select="$application_number" />
                </application_number>
                <receipt_date>
                    <xsl:value-of select="$receipt_date" />
                </receipt_date>
                <patent_name>
                    <xsl:value-of select="$patent_name" />
                </patent_name>
                <patent_name>
                    <xsl:value-of select="$patent_name" />
                </patent_name>
                <applicant>
                    <xsl:value-of select="$applicant" />
                </applicant>
            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>