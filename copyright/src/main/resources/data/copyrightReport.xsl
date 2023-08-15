<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="3.0"
    xmlns:rep="http://www.XMLproject.ftn.uns.ac.rs/copyrightReport">
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <style>
                    ::-webkit-scrollbar{
                    width: 0px;
                    height: 0px;
                    }
                    table,
                    td,
                    th {
                    font-family: Times New Roman;
                    padding: 5pt;
                    border: 1px solid black;
                    font-size: 9pt;
                    vertical-align: baseline;
                    }

                    table {
                    width: 100%;
                    margin: 0px;
                    border-collapse: collapse;
                    }

                    p {
                    margin: 4pt 0;
                    margin-bottom: 0pt;
                    text-indent: -0.55pt;
                    margin-left: 0.55pt;
                    }

                    .docx-wrapper {
                    background: white;
                    padding: 0px;
                    padding-bottom: 0px;
                    display: flex;
                    flex-flow: column;
                    align-items: center;
                    }

                    .docx-wrapper>section.docx {
                    background: white;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                    margin-bottom: 30px;
                    padding: 2.5rem;
                    }

                    .docx {
                    color: black;
                    padding: 0px;
                    width: 595pt;
                    min-height: 842pt;
                    column-count: 1;
                    column-gap: 36pt;
                    overflow: hidden;
                    }

                    section.docx {
                    box-sizing: border-box;
                    border-radius: 10px;
                    display: flex;
                    flex-flow: column nowrap;
                    position: relative;
                    }

                    section.docx>article {
                    margin-bottom: auto;
                    }

                    .docx table {
                    border-collapse: collapse;
                    }

                    .docx table td,
                    .docx table th {
                    vertical-align: top;
                    }

                    .docx p {
                    margin: 0pt;
                    min-height: 1em;
                    }

                    .docx span {
                    white-space: pre-wrap;
                    overflow-wrap: break-word;
                    }

                    .docx a {
                    color: inherit;
                    text-decoration: inherit;
                    }
                </style>
            </head>
            <body>
                <div class="docx-wrapper">
                    <section class="docx">
                        <article>
                            <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                                    style="min-height: 10pt; font-size: 10pt;">Република Србија</span></p>
                            <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                                    style="min-height: 10pt; font-size: 10pt;">Завод за интелектуалну својину</span></p>
                            <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                                    style="min-height: 10pt; font-size: 10pt;">Кнегиње Љубице 5</span></p>
                            <p style="margin-bottom: 0pt;"><span style="min-height: 10pt; font-size: 10pt;">11000 Београд</span></p>
                            <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"/>
                            <p style="margin-bottom: 0.25pt; text-align: center;"><span
                                    style="font-weight: bold; min-height: 12pt; font-size: 12pt;">ИЗВЕШТАЈ</span></p>
                            <p style="margin-bottom: 0.25pt; text-align: center;"><span
                                    style="font-weight: bold; min-height: 12pt; font-size: 12pt;">О ЗАХТЕВИМА ЗА ПРИЗНАЊЕ</span></p>
                            <p style="margin-bottom: 0.25pt; text-align: center;"><span
                                    style="font-weight: bold; min-height: 12pt; font-size: 12pt;">АУТОРСКИХ ДЕЛА</span></p>

                            <br/>
                            <p style="margin-bottom: 0.25pt; text-align: center;"><span
                                    style="font-weight: bold; min-height: 12pt; font-size: 12pt;">ОД <xsl:value-of select="//rep:startDate"/> ДО <xsl:value-of select="//rep:endDate"/></span></p>
                            <br/>
                            <br/>
                            <table style="border: 1.5px solid black;">
                                <colgroup>
                                    <col style="width: 30%"/>
                                    <col style="width: 70%"/>
                                </colgroup>
                                <tr>
                                    <td>Број поднетих пријава</td>
                                    <td style="text-align: center;"><xsl:value-of select="//rep:numberOfRequests"/></td>
                                </tr>
                                <tr>
                                    <td>Број прихваћених захтева</td>
                                    <td style="text-align: center;"><xsl:value-of select="//rep:numberOfAcceptedRequests"/></td>
                                </tr>
                                <tr>
                                    <td>Број одбијених захтева</td>
                                    <td style="text-align: center;">
                                        <xsl:value-of select="//rep:numberOfDeclinedRequests"/>
                                    </td>
                                </tr>
                            </table>
                        </article>
                    </section>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>