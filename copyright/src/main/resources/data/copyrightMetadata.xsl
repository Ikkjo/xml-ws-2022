<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:a1="http://www.XMLproject.ftn.uns.ac.rs/a-1"
                xmlns:user="http://www.XMLproject.ftn.uns.ac.rs/User">
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="request_number">
                <xsl:value-of select="//@request_number"/>
            </xsl:variable>
            <xsl:variable name="request_submission_date">
                <xsl:value-of select="//@request_submission_date"/>
            </xsl:variable>
            <xsl:variable name="title">
                <xsl:value-of select="//a1:Work_title/a1:Main_title"/>
            </xsl:variable>
            <xsl:variable name="work_type">
                <xsl:value-of select="//a1:Work_type"/>
            </xsl:variable>
            <xsl:variable name="applicant">
                <xsl:choose>
                    <xsl:when test="//a1:Applicant/user:TIndividual">
                        <xsl:value-of select="//a1:Applicant/user:TIndividual/user:First_name"/>
                        <xsl:text> </xsl:text>
                        <xsl:value-of select="//a1:Applicant/user:TIndividual/user:Last_name"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="//a1:Applicant/user:TLegal_entity/user:Business_name"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <xsl:variable name="attorney">
                <xsl:value-of select="//a1:Attorney/user:TIndividual/user:First_name"/>
                <xsl:text> </xsl:text>
                <xsl:value-of select="//a1:Attorney/user:TIndividual/user:Last_name"/>
            </xsl:variable>

            <rdf:Description rdf:about="{$request_number}">
                <a1:request_number>
                    <xsl:value-of select="$request_number"/>
                </a1:request_number>
                <a1:request_submission_date>
                    <xsl:value-of select="$request_submission_date"/>
                </a1:request_submission_date>
                <a1:title>
                    <xsl:value-of select="$title"/>
                </a1:title>
                <a1:work_type>
                    <xsl:value-of select="$work_type"/>
                </a1:work_type>
                <a1:applicant>
                    <xsl:value-of select="$applicant"/>
                </a1:applicant>
                <a1:attorney>
                    <xsl:value-of select="$attorney"/>
                </a1:attorney>
            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>