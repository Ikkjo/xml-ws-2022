<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:p1="http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognition"
                xmlns:user="http://www.XMLproject.ftn.uns.ac.rs/User">
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="Application_number">
                <xsl:value-of select="//p1:Information_for_institution/p1:Application_number" />
            </xsl:variable>
            <xsl:variable name="Receipt_date">
                <xsl:value-of select="//p1:Information_for_institution/p1:Receipt_date" />
            </xsl:variable>
            <xsl:variable name="Serbian_patent_name">
                <xsl:value-of select="//p1:Patent_name/p1:Serbian_patent_name" />
            </xsl:variable>
            <xsl:variable name="English_patent_name">
                <xsl:value-of select="//p1:Patent_name/p1:English_patent_name" />
            </xsl:variable>
            <xsl:variable name="Applicant">
                <xsl:choose>
                    <xsl:when test="//p1:Applicant/user:TIndividual">
                        <xsl:value-of select="//p1:Applicant/user:TIndividual/user:First_name"/>
                        <xsl:text> </xsl:text>
                        <xsl:value-of select="//p1:Applicant/user:TIndividual/user:Last_name"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="//p1:Applicant/user:TLegal_entity/user:Business_name"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <rdf:Description rdf:about="{$Application_number}">

                <Application_number>
                    <xsl:value-of select="$Application_number" />
                </Application_number>
                <Receipt_date>
                    <xsl:value-of select="$Receipt_date" />
                </Receipt_date>
                <Serbian_patent_name>
                    <xsl:value-of select="$Serbian_patent_name" />
                </Serbian_patent_name>
                <English_patent_name>
                    <xsl:value-of select="$English_patent_name" />
                </English_patent_name>
                <Applicant>
                    <xsl:value-of select="$Applicant" />
                </Applicant>
            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>