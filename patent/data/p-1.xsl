<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p1="http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognition"
                xmlns:user="http://www.XMLproject.ftn.uns.ac.rs/User"
                version="2.0">
    <xsl:template match="/">
        <!--docxjs library predefined styles-->
        <style>

            table, td, th {
            font-family: Times New Roman;
            padding : 5pt;
            border: 1px solid black;
            border-top:0px;
            font-size: 9pt;
            vertical-align: baseline;
            }

            table {
            width: 100%;
            margin:0px;
            border-collapse: collapse;
            }

            p {
            margin: 4pt 0;
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
            }

            .docx {
            color: black;
            }

            section.docx {
            box-sizing: border-box;
            display: flex;
            flex-flow: column nowrap;
            position: relative;
            overflow: hidden;
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
        </style><!--docxjs mathml polyfill styles-->
        <style>
            @namespace "http://www.w3.org/1998/Math/MathML";

            math {
            display: inline-block;
            line-height: initial;
            }

            mfrac {
            display: inline-block;
            vertical-align: -50%;
            text-align: center;
            }

            mfrac> :first-child {
            border-bottom: solid thin currentColor;
            }

            mfrac>* {
            display: block;
            }

            msub> :nth-child(2) {
            font-size: smaller;
            vertical-align: sub;
            }

            msup> :nth-child(2) {
            font-size: smaller;
            vertical-align: super;
            }

            munder,
            mover,
            munderover {
            display: inline-flex;
            flex-flow: column nowrap;
            vertical-align: middle;
            text-align: center;
            }

            munder> :not(:first-child),
            mover> :not(:first-child),
            munderover> :not(:first-child) {
            font-size: smaller;
            }

            munderover> :last-child {
            order: -1;
            }

            mroot,
            msqrt {
            position: relative;
            display: inline-block;
            border-top: solid thin currentColor;
            margin-top: 0.5px;
            vertical-align: middle;
            margin-left: 1ch;
            }

            mroot:before,
            msqrt:before {
            content: "";
            display: inline-block;
            position: absolute;
            width: 1ch;
            left: -1ch;
            top: -1px;
            bottom: 0;
            background-image: url("data:image/svg+xml,%3Csvg xmlns=%27http://www.w3.org/2000/svg%27 viewBox=%270 0 20 100%27 preserveAspectRatio=%27none%27%3E%3Cpath d=%27m0,75 l5,0 l5,25 l10,-100%27 stroke=%27black%27 fill=%27none%27 vector-effect=%27non-scaling-stroke%27/%3E%3C/svg%3E");
            }

            /*# sourceURL=webpack://./src/mathml.scss */
            /*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8uL3NyYy9tYXRobWwuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQSwrQ0FBQTtBQUVBO0VBQ0kscUJBQUE7RUFDQSxvQkFBQTtBQUFKOztBQUdBO0VBQ0kscUJBQUE7RUFDQSxvQkFBQTtFQUNBLGtCQUFBO0FBQUo7QUFFSTtFQUNJLHNDQUFBO0FBQVI7QUFHSTtFQUNJLGNBQUE7QUFEUjs7QUFNSTtFQUNJLGtCQUFBO0VBQ0EsbUJBQUE7QUFIUjs7QUFRSTtFQUNJLGtCQUFBO0VBQ0EscUJBQUE7QUFMUjs7QUFTQTtFQUNJLG9CQUFBO0VBQ0Esd0JBQUE7RUFDQSxzQkFBQTtFQUNBLGtCQUFBO0FBTko7QUFRSTtFQUNJLGtCQUFBO0FBTlI7O0FBV0k7RUFBZ0IsU0FBQTtBQVBwQjs7QUFVQTtFQUNJLGtCQUFBO0VBQ0EscUJBQUE7RUFDQSxtQ0FBQTtFQUNBLGlCQUFBO0VBQ0Esc0JBQUE7RUFDQSxnQkFBQTtBQVBKO0FBU0k7RUFDSSxXQUFBO0VBQ0EscUJBQUE7RUFDQSxrQkFBQTtFQUNBLFVBQUE7RUFDQSxVQUFBO0VBQ0EsU0FBQTtFQUNBLFNBQUE7RUFDQSx5REFBQTtBQVBSIiwic291cmNlc0NvbnRlbnQiOlsiQG5hbWVzcGFjZSBcImh0dHA6Ly93d3cudzMub3JnLzE5OTgvTWF0aC9NYXRoTUxcIjtcclxuXHJcbm1hdGgge1xyXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xyXG4gICAgbGluZS1oZWlnaHQ6IGluaXRpYWw7XHJcbn1cclxuXHJcbm1mcmFjIHtcclxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcclxuICAgIHZlcnRpY2FsLWFsaWduOiAtNTAlO1xyXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG5cclxuICAgICY+OmZpcnN0LWNoaWxkIHtcclxuICAgICAgICBib3JkZXItYm90dG9tOiBzb2xpZCB0aGluIGN1cnJlbnRDb2xvcjtcclxuICAgIH1cclxuXHJcbiAgICAmPioge1xyXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrO1xyXG4gICAgfVxyXG59XHJcblxyXG5tc3ViIHtcclxuICAgICY+Om50aC1jaGlsZCgyKSB7XHJcbiAgICAgICAgZm9udC1zaXplOiBzbWFsbGVyO1xyXG4gICAgICAgIHZlcnRpY2FsLWFsaWduOiBzdWI7XHJcbiAgICB9XHJcbn1cclxuXHJcbm1zdXAge1xyXG4gICAgJj46bnRoLWNoaWxkKDIpIHtcclxuICAgICAgICBmb250LXNpemU6IHNtYWxsZXI7XHJcbiAgICAgICAgdmVydGljYWwtYWxpZ246IHN1cGVyO1xyXG4gICAgfVxyXG59XHJcblxyXG5tdW5kZXIsIG1vdmVyLCBtdW5kZXJvdmVyIHtcclxuICAgIGRpc3BsYXk6IGlubGluZS1mbGV4O1xyXG4gICAgZmxleC1mbG93OiBjb2x1bW4gbm93cmFwO1xyXG4gICAgdmVydGljYWwtYWxpZ246IG1pZGRsZTtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxuXHJcbiAgICAmPjpub3QoOmZpcnN0LWNoaWxkKSB7XHJcbiAgICAgICAgZm9udC1zaXplOiBzbWFsbGVyO1xyXG4gICAgfVxyXG59XHJcblxyXG5tdW5kZXJvdmVyIHtcclxuICAgICY+Omxhc3QtY2hpbGQgeyBvcmRlcjogLTE7IH1cclxufVxyXG5cclxubXJvb3QsIG1zcXJ0IHtcclxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcclxuICAgIGJvcmRlci10b3A6IHNvbGlkIHRoaW4gY3VycmVudENvbG9yOyAgXHJcbiAgICBtYXJnaW4tdG9wOiAwLjVweDtcclxuICAgIHZlcnRpY2FsLWFsaWduOiBtaWRkbGU7ICBcclxuICAgIG1hcmdpbi1sZWZ0OiAxY2g7IFxyXG5cclxuICAgICY6YmVmb3JlIHtcclxuICAgICAgICBjb250ZW50OiBcIlwiO1xyXG4gICAgICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgd2lkdGg6IDFjaDtcclxuICAgICAgICBsZWZ0OiAtMWNoO1xyXG4gICAgICAgIHRvcDogLTFweDtcclxuICAgICAgICBib3R0b206IDA7XHJcbiAgICAgICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiZGF0YTppbWFnZS9zdmcreG1sLCUzQ3N2ZyB4bWxucz0naHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmcnIHZpZXdCb3g9JzAgMCAyMCAxMDAnIHByZXNlcnZlQXNwZWN0UmF0aW89J25vbmUnJTNFJTNDcGF0aCBkPSdtMCw3NSBsNSwwIGw1LDI1IGwxMCwtMTAwJyBzdHJva2U9J2JsYWNrJyBmaWxsPSdub25lJyB2ZWN0b3ItZWZmZWN0PSdub24tc2NhbGluZy1zdHJva2UnLyUzRSUzQy9zdmclM0VcIik7XHJcbiAgICB9XHJcbn0iXSwic291cmNlUm9vdCI6IiJ9 */
        </style><!--docxjs document theme values-->
        <style>
            .docx {
            --docx-majorHAnsi-font: Calibri Light;
            --docx-minorHAnsi-font: Calibri;
            --docx-dk1-color: #000000;
            --docx-lt1-color: #FFFFFF;
            --docx-dk2-color: #44546A;
            --docx-lt2-color: #E7E6E6;
            --docx-accent1-color: #5B9BD5;
            --docx-accent2-color: #ED7D31;
            --docx-accent3-color: #A5A5A5;
            --docx-accent4-color: #FFC000;
            --docx-accent5-color: #4472C4;
            --docx-accent6-color: #70AD47;
            --docx-hlink-color: #0563C1;
            --docx-folHlink-color: #954F72;
            }
        </style><!--docxjs document styles-->
        <style>
            .docx span {
            font-family: Times New Roman;
            }

            .docx p,
            p.docx_1 {
            margin-bottom: 0.15pt;
            line-height: 1.08;
            text-indent: -0.50pt;
            margin-left: 0.50pt;
            }

            .docx p,
            p.docx_1 span {
            font-family: Times New Roman;
            color: #000000;
            min-height: 8.00pt;
            font-size: 8.00pt;
            }

            .docx table,
            table.docx_9 td {
            padding-top: 0.00pt;
            padding-left: 5.40pt;
            padding-bottom: 0.00pt;
            padding-right: 5.40pt;
            }

            table.docx_32 p {
            line-height: 1.00;
            }

            table.docx_32 td {
            padding-top: 3.00pt;
            padding-left: 7.10pt;
            padding-bottom: 3.00pt;
            padding-right: 5.75pt;
            }

        </style>
        <div class="docx-wrapper">
            <section class="docx"
                     style="padding: 0px; width: 595pt; min-height: 842pt; column-count: 1; column-gap: 36pt;">
                <header>
                    <p style="margin-bottom: 0pt; text-indent: 0pt; margin-left: 0pt; margin-right: 0.35pt; text-align: right;">
                        <span>Образац П-1</span></p>
                    <p style="margin-bottom: 0pt; text-indent: 0pt; margin-left: 0pt; margin-right: 0.35pt; text-align: right;"/>
                    <p style="margin-bottom: 0pt; text-indent: 0pt; margin-left: 0pt; margin-right: 0.35pt; text-align: right;"/>
                </header>
                <article>
                    <p
                            style="border-width: 0pt; border-style: solid; border-color: black; background-color: inherit; margin: 0pt; line-height: 1.15; text-indent: 0pt; text-align: left;">
                    </p>
                    <table class="docx_32" style="width: 322.75pt; border-top: 0px solid black; border-top: 1px solid black;">

                        <tr style="height: 19.05pt;">
                            <td style="width: 88pt;">
                                <p>Број пријаве</p>
                            </td>
                            <td style="width: 264pt">
                                <p style="min-height: 16pt; font-size: 16pt;">
                                    П-<xsl:value-of select="//p1:Information_for_institution/p1:Application_number"/>
                                </p>
                            </td>
                        </tr>


                    </table>
                    <table class="docx_32" style="width: 322.75pt; table-layout: auto;">


                        <tr style="height: 19.35pt;">
                            <td style="width: 176pt">
                                <p>Датум пријема</p>
                                <p style="margin: 3pt 0 0 15pt"><xsl:value-of select="//p1:Information_for_institution/p1:Receipt_date"/></p>
                            </td>
                            <td style="width: 176pt">
                                <p>Признати датум подношења</p>
                                <p style="margin: 3pt 0 0 15pt"><xsl:value-of select="//p1:Information_for_institution/p1:Submission_date"/></p>
                            </td>
                        </tr>

                    </table>
                    <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                            style="min-height: 10pt; font-size: 10pt;"> Република Србија</span></p>
                    <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                            style="min-height: 10pt; font-size: 10pt;"> Завод за интелектуалну својину</span></p>
                    <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                            style="min-height: 10pt; font-size: 10pt;"> Кнегиње Љубице број 5</span></p>
                    <p style="margin-bottom: 0pt;"><span style="min-height: 10pt; font-size: 10pt;"> 11000 Београд</span></p>
                    <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"/>
                    <p style="margin-bottom: 0.25pt; text-align: center;"><span
                            style="font-weight: bold; min-height: 12pt; font-size: 12pt;">ЗАХТЕВ</span></p>
                    <p style="margin-bottom: 0.25pt; text-align: center;"><span
                            style="font-weight: bold; min-height: 12pt; font-size: 12pt;">ЗА ПРИЗНАЊЕ ПАТЕНТА</span></p>
                    <p style="margin-bottom: 0.25pt; text-indent: 0pt; margin-left: 0pt;"/>
                    <p style="margin-bottom: 0.25pt; text-indent: 0pt; margin-left: 0pt;"/>
                    <p style="margin-bottom: 0.25pt; text-indent: 0pt; margin-left: 0pt; text-align: center;"/>
                    <p style="margin-bottom: 0.25pt; text-indent: 0pt; margin-left: 0pt; text-align: center;"/>
                    <table style="border: 1.5px solid black;">
                        <colgroup>
                            <col style="width: 33.33%;"/>
                            <col style="width: 33.33%;"/>
                            <col style="width: 33.33%;"/>
                        </colgroup>
                        <!--                POLJE BROJ I                -->
                        <tr>
                            <td colspan="3" style="font-weight: bold; border: 1.5px solid black;">
                                <p>Поље број I : НАЗИВ ПРОНАЛАСКА</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <p>На српском језику: <xsl:value-of select="//p1:Patent_name/p1:Serbian_patent_name"/></p>
                                <p>На енглеском језику: <xsl:value-of select="//p1:Patent_name/p1:English_patent_name"/></p>
                            </td>
                        </tr>
                        <!--                POLJE BROJ II                -->
                        <tr>
                            <td colspan="3" style="font-weight: bold; border: 1.5px solid black;">
                                <p>
                                    Поље број II : ПОДНОСИЛАЦ ПРИЈАВЕ
                                    <!-- Nedostaje atribut applicantIsInventor u klasi -->
                                    (<xsl:choose>
                                    <xsl:when test="//@applicantIsInventor = 'true'"><span style="min-height: 9pt; font-size: 9pt;"> Подносилац пријаве је и проналазач</span></xsl:when>
                                    <xsl:otherwise><span style="min-height: 9pt; font-size: 9pt;"> Подносилац пријаве није проналазач</span></xsl:otherwise>
                                </xsl:choose>)
                                </p>
                            </td>
                        </tr>
                        <tr style="height: 40pt;">
                            <td  rowspan="2">
                                <p>Име и презиме / Пословно име: </p>
                                <p><xsl:choose>
                                    <xsl:when test="//p1:Applicant//user:TIndividual">
                                        <xsl:value-of select="//p1:Applicant/user:First_name"/>
                                        <xsl:text> </xsl:text>
                                        <xsl:value-of select="//p1:Applicant/user:Last_name"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="//p1:Applicant/user:Business_name"/>
                                    </xsl:otherwise>
                                </xsl:choose></p>
                            </td>
                            <td rowspan="2">
                                <p>Улица и број, поштански број, место и држава:</p>
                                <p><xsl:value-of select="//p1:Applicant/user:Address/user:Street"/>
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//p1:Applicant/user:Address/user:Street_number"/></p>
                                <p><xsl:value-of select="//p1:Applicant//user:Address/user:Zip_code"/></p>
                                <p><xsl:value-of select="//p1:Applicant/user:Address/user:City"/></p>
                                <p><xsl:value-of select="//p1:Applicant/user:Address/user:Drzava"/></p>
                            </td>
                            <td>
                                <p>Број телефона:</p>
                                <p><xsl:value-of select="//p1:Applicant/user:Phone_number"/></p>
                            </td>
                        </tr>
                        <tr style="height: 40pt;">
                            <td>
                                <p>Број факса: </p>
                            <p><xsl:value-of select="//p1:Applicant/user:Fax_number"/></p>
                    </td>
                </tr>
                <tr style="height: 40pt;">
                    <td colspan="2">
                        <p>
                            Држављанство:
                            <xsl:value-of select="//p1:Applicant/user:Citizenship"/>
                        </p>
                    </td>
                    <td>
                        <p>Е-пошта: </p>
                        <p><xsl:value-of select="//p1:Applicant/user:Email"/></p>
                    </td>
                </tr>
                <!--                POLJE BROJ III                -->
                        <tr>
                            <td colspan="3" style="font-weight: bold; border: 1.5px solid black;">
                                <p>
                                    Поље број III : ПРОНАЛАЗАЧ
                                    <xsl:choose>
                                        <xsl:when test="//@does_not_want_to_be_listed">
                                            Проналазач не жели да буде наведен у пријави
                                        </xsl:when>
                                        <xsl:otherwise> </xsl:otherwise>
                                    </xsl:choose>
                                </p>
                            </td>
                        </tr>
                        <tr style="height: 40pt;">
                            <td  rowspan="3">
                                <p>Име и презиме: </p>
                                <p><xsl:value-of select="//p1:Inventor/user:First_name"/>
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//p1:Inventor/user:Last_name"/>
                                </p>
                            </td>
                            <td rowspan="3">
                                <p>Улица и број, поштански број, место и држава:</p>
                                <p><xsl:value-of select="//p1:Inventor/user:Address/user:Street"/>
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//p1:Inventor/user:Address/user:Street_number"/></p>
                                <p><xsl:value-of select="//p1:Inventor/user:Address/user:Zip_code"/></p>
                                <p><xsl:value-of select="//p1:Inventor/user:Address/user:City"/></p>
                                <p><xsl:value-of select="//p1:Inventor/user:Address/user:Drzava"/></p>
                            </td>
                            <td>
                                <p>Број телефона:</p>
                                <p><xsl:value-of select="//p1:Inventor/user:Phone_number"/></p>
                            </td>
                        </tr>
                        <tr style="height: 40pt;">
                            <td>
                                <p>Број факса: </p>
                                <p><xsl:value-of select="//p1:Inventor/user:Fax_number"/></p>
                            </td>
                        </tr>
                        <tr style="height: 40pt;">
                            <td>
                                <p>Е-пошта: </p>
                                <p><xsl:value-of select="//p1:Inventor/user:Email"/></p>
                            </td>
                        </tr>
                        <!--                POLJE BROJ IV                -->
                        <tr>
                            <td colspan="3" style="font-weight: bold; border: 1.5px solid black;">
                                <p>
                                    Поље број IV :
                                    <xsl:choose>
                                        <xsl:when test="//@proxy_for_representation = 'true'">
                                            ПУНОМОЋНИК ЗА ЗАСТУПАЊЕ
                                        </xsl:when>
                                    </xsl:choose>
                                    <xsl:choose>
                                        <xsl:when test="//@attorney_for_receiving_letters = 'true'">
                                            ПУНОМОЋНИК ЗА ПРИЈЕМ ПИСМЕНА
                                        </xsl:when>
                                    </xsl:choose>
                                </p>
                            </td>
                        </tr>
                        <tr style="height: 40pt;">
                            <td  rowspan="3">
                                <p>Име и презиме / Пословно име: </p>
                                <p><xsl:choose>
                                    <xsl:when test="//p1:Proxy//user:TIndividual">
                                        <xsl:value-of select="//p1:Proxy/user:First_name"/>
                                        <xsl:text> </xsl:text>
                                        <xsl:value-of select="//p1:Proxy/user:Last_name"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="//p1:Proxy/user:Business_name"/>
                                    </xsl:otherwise>
                                </xsl:choose></p>
                            </td>
                            <td rowspan="3">
                                <p>Улица и број, поштански број, место и држава:</p>
                                <p><xsl:value-of select="//p1:Proxy/user:Address/user:Street"/>
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//p1:Proxy/user:Address/user:Street_number"/></p>
                                <p><xsl:value-of select="//p1:Proxy/user:Address/user:Zip_code"/></p>
                                <p><xsl:value-of select="//p1:Proxy/user:Address/user:City"/></p>
                                <p><xsl:value-of select="//p1:Proxy/user:Address/user:Drzava"/></p>
                            </td>
                            <td>
                                <p>Број телефона:</p>
                                <p><xsl:value-of select="//p1:Proxy/user:Phone_number"/></p>
                            </td>
                        </tr>
                <tr style="height: 40pt;">
                    <td>
                        <p>Е-пошта: </p>
                        <p><xsl:value-of select="//p1:Proxy/user:Email"/></p>
                    </td>
                </tr>
                <!--                POLJE BROJ V                -->
                    </table>
                    <table style="border: 1.5px solid black; border-top:0px;">
                        <colgroup>
                            <col style="width: 33.33%;"/>
                            <col style="width: 33.33%;"/>
                            <col style="width: 33.33%;"/>
                        </colgroup>
                        <tr>
                            <td colspan="3" style="font-weight: bold; border: 1.5px solid black;">
                                <p>Поље број V : АДРЕСА ЗА ДОСТАВЉАЊЕ</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <p>
                                    Улица и број, поштански број и место:
                                    <xsl:value-of select="//p1:Delivery_address/user:Address/user:Street"/>
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//p1:Delivery_address/user:Address/user:Street_number"/>,
                                    <xsl:value-of select="//p1:Delivery_address/user:Address/user:Zip_code"/>,
                                    <xsl:value-of select="//p1:Delivery_address/user:Address/user:City"/>,
                                    <xsl:value-of select="//p1:Delivery_address/user:Address/user:Drzava"/>
                                </p>
                            </td>
                        </tr>
                        <!--                POLJE BROJ VI                -->
                        <tr>
                            <td colspan="3" style="font-weight: bold; border: 1.5px solid black;">
                                <p>Поље број VI : НАЧИН ДОСТАВЉАЊА</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <p>
                                    <xsl:choose>
                                        <xsl:when test="//p1:Delivery_type/p1:Electronic_delivery = 'true'"><span style="min-height: 9pt; font-size: 9pt;">Подносилац пријаве је сагласан да Завод врши достављање писмена електронским путем у форми електронског документа </span></xsl:when>
                                        <xsl:otherwise><span style="min-height: 9pt; font-size: 9pt;">Подносилац пријаве није сагласан да Завод врши достављање писмена електронским путем у форми електронског документа </span></xsl:otherwise>
                                    </xsl:choose>
                                </p>
                                <p>
                                    <xsl:choose>
                                        <xsl:when test="//p1:Delivery_type/p1:Delivery_in_paper_form = 'true'"><span style="min-height: 9pt; font-size: 9pt;">Подносилац пријаве је сагласан да Завод врши достављање писмена у папирној форми </span></xsl:when>
                                        <xsl:otherwise><span style="min-height: 9pt; font-size: 9pt;">Подносилац пријаве није сагласан да Завод врши достављање писмена у папирној форми </span></xsl:otherwise>
                                    </xsl:choose>
                                </p>
                            </td>
                        </tr>
                        <!--                POLJE BROJ VII                -->
                        <tr>
                            <td colspan="3" style="font-weight: bold; border: 1.5px solid black;">
                                <p>Поље број VII :   ДОПУНСКА ПРИЈАВА </p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <p>
                                    Број првобитне пријаве / основне пријаве, односно основног патента: П-<xsl:value-of select="//p1:Application_information/p1:Original_application_number"/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <p>
                                    Датум подношења првобитнe пријаве / основне пријаве:
                                    <xsl:value-of select="/p1:Application_information/p1:Original_application_submission_date"/>
                                </p>
                            </td>
                        </tr>
                        <!--                POLJE BROJ VIII                -->
                        <tr>
                            <td colspan="3" style="font-weight: bold; border: 1.5px solid black;">
                                <p>Поље број VIII : ЗАХТЕВ ЗА ПРИЗНАЊЕ ПРАВА ПРВЕНСТВА ИЗ РАНИЈИХ ПРИЈАВА: </p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="padding:0px">
                                <table style="border:0;">
                                    <colgroup>
                                        <col style="width: 10%;"/>
                                        <col style="width: 30%;"/>
                                        <col style="width: 30%;"/>
                                        <col style="width: 30%;"/>
                                    </colgroup>
                                    <tr  style="height: 17pt;">
                                        <td><p>Редни број</p></td>
                                        <td><p>Датум подношења раније пријаве</p></td>
                                        <td><p>Број раније пријаве</p></td>
                                        <td><p>Двословна ознака државе или организације</p></td>
                                    </tr>
                                    <xsl:for-each select="//p1:Priority_rights_recognition_from_earlier_applications/p1:Earlier_applications">
                                        <tr style="height: 17pt;">
                                            <td>
                                                <p><xsl:value-of select="position()"/>.</p>
                                            </td>
                                            <td>
                                                <p><xsl:value-of select="//p1:Earlier_application/p1:Earlier_application_submission_date"/></p>
                                            </td>
                                            <td>
                                                <p><xsl:value-of select="//p1:Earlier_application/p1:Earlier_application_number"/></p>
                                            </td>
                                            <td>
                                                <p><xsl:value-of select="//p1:Earlier_application/p1:Country_or_organization_designation"/></p>
                                            </td>
                                        </tr>
                                    </xsl:for-each>
                                </table>
                            </td>
                        </tr>

                    </table>
                    <p style="margin-bottom: 8pt; text-indent: 0pt; margin-left: 0pt;"/>
                </article>
            </section>
        </div>
    </xsl:template>
</xsl:stylesheet>