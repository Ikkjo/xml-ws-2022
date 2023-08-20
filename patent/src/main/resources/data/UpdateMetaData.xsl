<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:p1="http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognition"
                xmlns:user="http://www.XMLproject.ftn.uns.ac.rs/User">
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="application_number">
                <xsl:value-of select="//p1:Information_for_institution/p1:Application_number" />
            </xsl:variable>
            <xsl:variable name="receipt_date">
                <xsl:value-of select="//p1:Information_for_institution/p1:Receipt_date" />
            </xsl:variable>
            <xsl:variable name="submission_date">
                <xsl:value-of select="//p1:Information_for_institution/p1:Submission_date" />
            </xsl:variable>
            <xsl:variable name="is_accepted">
                <xsl:value-of select="//@is_accepted" />
            </xsl:variable>
            <xsl:variable name="serbian_patent_name">
                <xsl:value-of select="//p1:Patent_name/p1:Serbian_patent_name" />
            </xsl:variable>
            <xsl:variable name="english_patent_name">
                <xsl:value-of select="//p1:Patent_name/p1:English_patent_name" />
            </xsl:variable>
            <xsl:variable name="applicant">
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
            <rdf:Description rdf:about="{$application_number}">
                <p1:Application_number>
                    <xsl:value-of select="$application_number" />
                </p1:Application_number>
                <p1:Receipt_date>
                    <xsl:value-of select="$receipt_date" />
                </p1:Receipt_date>
                <p1:Submission_date>
                    <xsl:value-of select="$submission_date" />
                </p1:Submission_date>
                <p1:Serbian_patent_name>
                    <xsl:value-of select="$serbian_patent_name" />
                </p1:Serbian_patent_name>
                <p1:English_patent_name>
                    <xsl:value-of select="$english_patent_name" />
                </p1:English_patent_name>
                <p1:Applicant>
                    <xsl:value-of select="$applicant" />
                </p1:Applicant>
                <!-- Treba dodati atribut is_accepted-->
                <p1:is_accepted>
                    <xsl:value-of select="$is_accepted" />
                </p1:is_accepted>
            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>