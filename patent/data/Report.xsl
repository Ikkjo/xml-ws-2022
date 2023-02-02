<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:r="http://www.XMLproject.ftn.uns.ac.rs/Report"
                version="2.0">
    <xsl:template match="/">
        <!--docxjs library predefined styles-->
        <style>

            table, td, th {
            font-family: Arial;
            padding : 5pt;
            border: 1px solid black;
            font-size: 11pt;
            vertical-align: baseline;
            }

            table {
            width: 96%;
            margin: 2%;
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
                <article>
                    <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                            style="min-height: 10pt; font-size: 10pt; font-family: Arial;"> Република Србија</span></p>
                    <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                            style="min-height: 10pt; font-size: 10pt; font-family: Arial;"> Завод за интелектуалну својину</span></p>
                    <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"><span
                            style="min-height: 10pt; font-size: 10pt; font-family: Arial;"> Кнегиње Љубице број 5</span></p>
                    <p style="margin-bottom: 0pt;"><span style="min-height: 10pt; font-size: 10pt; font-family: Arial;"> 11000 Београд</span></p>
                    <p style="margin-bottom: 0pt; text-indent: -0.55pt; margin-left: 0.55pt;"/>
                    <p style="margin-bottom: 0.25pt; text-align: center;"><span
                            style="font-weight: bold; min-height: 12pt; font-size: 14pt; font-family: Arial;">ИЗВЕШТАЈ</span></p>
                    <p style="margin-bottom: 0.25pt; text-align: center;"><span
                            style="font-weight: bold; min-height: 12pt; font-size: 12pt;"> </span></p>
                    <p style="margin-bottom: 0.25pt; text-indent: 0pt; margin-left: 0pt;"> </p>
                    <p style="margin-bottom: 0.25pt; text-indent: 0pt; margin-left: 0pt;"/>
                    <p style="margin-bottom: 0.25pt; text-indent: 0pt; margin-left: 0pt; text-align: center;"/>
                    <p style="margin-bottom: 0.25pt; text-indent: 0pt; margin-left: 0pt; text-align: center;"/>
                    <table style="border: 1.5px solid black;">
                        <colgroup>
                            <col style="width: 35%"/>
                            <col style="width: 65%;"/>
                        </colgroup>
                        <tr>
                            <td style="font-weight: bold;">Почетни датум</td>
                            <td><xsl:value-of select="//r:Start_date"/></td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold;">Крајњи датум</td>
                            <td><xsl:value-of select="//r:End_date"/></td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold;">Број поднетих захтева</td>
                            <td><xsl:value-of select="//r:Submitted_requests_number"/></td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold;">Број прихваћених захтева</td>
                            <td><xsl:value-of select="//r:Accepted_requests_number"/></td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold;">Број одбијених захтева</td>
                            <td><xsl:value-of select="//r:Rejected_requests_number"/></td>
                        </tr>

                    </table>
                    <p style="margin-bottom: 8pt; text-indent: 0pt; margin-left: 0pt;"/>
                </article>
            </section>
        </div>
    </xsl:template>
</xsl:stylesheet>