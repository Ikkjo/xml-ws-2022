<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a1="http://www.XMLproject.ftn.uns.ac.rs/a-1"
                xmlns:user="http://www.XMLproject.ftn.uns.ac.rs/User"
                version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <!--docxjs library predefined styles--><style>
                .docx-wrapper { padding: 30px; padding-bottom: 0px; display: flex; flex-flow: column; align-items: center; }
                .docx-wrapper section.docx { background: white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); margin-bottom: 30px; }
                .docx { color: black; }
                section.docx { box-sizing: border-box; display: flex; flex-flow: column nowrap; position: relative; overflow: hidden; }
                section.docx article { margin-bottom: auto; }
                .docx table { border-collapse: collapse; }
                .docx table td, .docx table th { vertical-align: top; }
                .docx p { margin: 1rem 0; min-height: 1em; }
                .docx span { white-space: pre-wrap; overflow-wrap: break-word; }
                .docx a { color: inherit; text-decoration: inherit; }
                </style><!--docxjs mathml polyfill styles--><style>@namespace "http://www.w3.org/1998/Math/MathML";
                    math {
                    display: inline-block;
                    line-height: initial;
                    }

                    mfrac {
                    display: inline-block;
                    vertical-align: -50%;
                    text-align: center;
                    }
                    mfrac > :first-child {
                    border-bottom: solid thin currentColor;
                    }
                    mfrac > * {
                    display: block;
                    }

                    msub > :nth-child(2) {
                    font-size: smaller;
                    vertical-align: sub;
                    }

                    msup > :nth-child(2) {
                    font-size: smaller;
                    vertical-align: super;
                    }

                    munder, mover, munderover {
                    display: inline-flex;
                    flex-flow: column nowrap;
                    vertical-align: middle;
                    text-align: center;
                    }
                    munder > :not(:first-child), mover > :not(:first-child), munderover > :not(:first-child) {
                    font-size: smaller;
                    }

                    munderover > :last-child {
                    order: -1;
                    }

                    mroot, msqrt {
                    position: relative;
                    display: inline-block;
                    border-top: solid thin currentColor;
                    margin-top: 0.5px;
                    vertical-align: middle;
                    margin-left: 1ch;
                    }
                    mroot:before, msqrt:before {
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
                    /*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8uL3NyYy9tYXRobWwuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQSwrQ0FBQTtBQUVBO0VBQ0kscUJBQUE7RUFDQSxvQkFBQTtBQUFKOztBQUdBO0VBQ0kscUJBQUE7RUFDQSxvQkFBQTtFQUNBLGtCQUFBO0FBQUo7QUFFSTtFQUNJLHNDQUFBO0FBQVI7QUFHSTtFQUNJLGNBQUE7QUFEUjs7QUFNSTtFQUNJLGtCQUFBO0VBQ0EsbUJBQUE7QUFIUjs7QUFRSTtFQUNJLGtCQUFBO0VBQ0EscUJBQUE7QUFMUjs7QUFTQTtFQUNJLG9CQUFBO0VBQ0Esd0JBQUE7RUFDQSxzQkFBQTtFQUNBLGtCQUFBO0FBTko7QUFRSTtFQUNJLGtCQUFBO0FBTlI7O0FBV0k7RUFBZ0IsU0FBQTtBQVBwQjs7QUFVQTtFQUNJLGtCQUFBO0VBQ0EscUJBQUE7RUFDQSxtQ0FBQTtFQUNBLGlCQUFBO0VBQ0Esc0JBQUE7RUFDQSxnQkFBQTtBQVBKO0FBU0k7RUFDSSxXQUFBO0VBQ0EscUJBQUE7RUFDQSxrQkFBQTtFQUNBLFVBQUE7RUFDQSxVQUFBO0VBQ0EsU0FBQTtFQUNBLFNBQUE7RUFDQSx5REFBQTtBQVBSIiwic291cmNlc0NvbnRlbnQiOlsiQG5hbWVzcGFjZSBcImh0dHA6Ly93d3cudzMub3JnLzE5OTgvTWF0aC9NYXRoTUxcIjtcclxuXHJcbm1hdGgge1xyXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xyXG4gICAgbGluZS1oZWlnaHQ6IGluaXRpYWw7XHJcbn1cclxuXHJcbm1mcmFjIHtcclxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcclxuICAgIHZlcnRpY2FsLWFsaWduOiAtNTAlO1xyXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG5cclxuICAgICY+OmZpcnN0LWNoaWxkIHtcclxuICAgICAgICBib3JkZXItYm90dG9tOiBzb2xpZCB0aGluIGN1cnJlbnRDb2xvcjtcclxuICAgIH1cclxuXHJcbiAgICAmPioge1xyXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrO1xyXG4gICAgfVxyXG59XHJcblxyXG5tc3ViIHtcclxuICAgICY+Om50aC1jaGlsZCgyKSB7XHJcbiAgICAgICAgZm9udC1zaXplOiBzbWFsbGVyO1xyXG4gICAgICAgIHZlcnRpY2FsLWFsaWduOiBzdWI7XHJcbiAgICB9XHJcbn1cclxuXHJcbm1zdXAge1xyXG4gICAgJj46bnRoLWNoaWxkKDIpIHtcclxuICAgICAgICBmb250LXNpemU6IHNtYWxsZXI7XHJcbiAgICAgICAgdmVydGljYWwtYWxpZ246IHN1cGVyO1xyXG4gICAgfVxyXG59XHJcblxyXG5tdW5kZXIsIG1vdmVyLCBtdW5kZXJvdmVyIHtcclxuICAgIGRpc3BsYXk6IGlubGluZS1mbGV4O1xyXG4gICAgZmxleC1mbG93OiBjb2x1bW4gbm93cmFwO1xyXG4gICAgdmVydGljYWwtYWxpZ246IG1pZGRsZTtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxuXHJcbiAgICAmPjpub3QoOmZpcnN0LWNoaWxkKSB7XHJcbiAgICAgICAgZm9udC1zaXplOiBzbWFsbGVyO1xyXG4gICAgfVxyXG59XHJcblxyXG5tdW5kZXJvdmVyIHtcclxuICAgICY+Omxhc3QtY2hpbGQgeyBvcmRlcjogLTE7IH1cclxufVxyXG5cclxubXJvb3QsIG1zcXJ0IHtcclxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcclxuICAgIGJvcmRlci10b3A6IHNvbGlkIHRoaW4gY3VycmVudENvbG9yOyAgXHJcbiAgICBtYXJnaW4tdG9wOiAwLjVweDtcclxuICAgIHZlcnRpY2FsLWFsaWduOiBtaWRkbGU7ICBcclxuICAgIG1hcmdpbi1sZWZ0OiAxY2g7IFxyXG5cclxuICAgICY6YmVmb3JlIHtcclxuICAgICAgICBjb250ZW50OiBcIlwiO1xyXG4gICAgICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgd2lkdGg6IDFjaDtcclxuICAgICAgICBsZWZ0OiAtMWNoO1xyXG4gICAgICAgIHRvcDogLTFweDtcclxuICAgICAgICBib3R0b206IDA7XHJcbiAgICAgICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiZGF0YTppbWFnZS9zdmcreG1sLCUzQ3N2ZyB4bWxucz0naHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmcnIHZpZXdCb3g9JzAgMCAyMCAxMDAnIHByZXNlcnZlQXNwZWN0UmF0aW89J25vbmUnJTNFJTNDcGF0aCBkPSdtMCw3NSBsNSwwIGw1LDI1IGwxMCwtMTAwJyBzdHJva2U9J2JsYWNrJyBmaWxsPSdub25lJyB2ZWN0b3ItZWZmZWN0PSdub24tc2NhbGluZy1zdHJva2UnLyUzRSUzQy9zdmclM0VcIik7XHJcbiAgICB9XHJcbn0iXSwic291cmNlUm9vdCI6IiJ9 */</style><!--docxjs document theme values--><style>.docx {
                    --docx-majorHAnsi-font: Calibri;
                    --docx-minorHAnsi-font: Cambria;
                    --docx-dk1-color: #000000;
                    --docx-lt1-color: #FFFFFF;
                    --docx-dk2-color: #1F497D;
                    --docx-lt2-color: #EEECE1;
                    --docx-accent1-color: #4F81BD;
                    --docx-accent2-color: #C0504D;
                    --docx-accent3-color: #9BBB59;
                    --docx-accent4-color: #8064A2;
                    --docx-accent5-color: #4BACC6;
                    --docx-accent6-color: #F79646;
                    --docx-hlink-color: #0000FF;
                    --docx-folHlink-color: #800080;
                    }
                </style><!--docxjs document styles--><style>.docx span {
                    }
                    .docx p {
                    }
                    p.docx_heading1 {
                    text-align: center;
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_heading1 span {
                    font-family: Arial;
                    font-weight: bold;
                    min-height: 12.00pt;
                    font-size: 12.00pt;
                    verticalAlign: -0.50pt;
                    }
                    p.docx_heading2 {
                    margin-top: 18.00pt;
                    margin-bottom: 4.00pt;
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_heading2 span {
                    font-weight: bold;
                    min-height: 18.00pt;
                    font-size: 18.00pt;
                    verticalAlign: -0.50pt;
                    }
                    p.docx_heading3 {
                    margin-top: 14.00pt;
                    margin-bottom: 4.00pt;
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_heading3 span {
                    font-weight: bold;
                    min-height: 14.00pt;
                    font-size: 14.00pt;
                    verticalAlign: -0.50pt;
                    }
                    p.docx_heading4 {
                    margin-top: 12.00pt;
                    margin-bottom: 2.00pt;
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_heading4 span {
                    font-weight: bold;
                    min-height: 12.00pt;
                    font-size: 12.00pt;
                    verticalAlign: -0.50pt;
                    }
                    p.docx_heading5 {
                    margin-top: 11.00pt;
                    margin-bottom: 2.00pt;
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_heading5 span {
                    font-weight: bold;
                    min-height: 11.00pt;
                    font-size: 11.00pt;
                    verticalAlign: -0.50pt;
                    }
                    p.docx_heading6 {
                    margin-top: 10.00pt;
                    margin-bottom: 2.00pt;
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_heading6 span {
                    font-weight: bold;
                    min-height: 10.00pt;
                    font-size: 10.00pt;
                    verticalAlign: -0.50pt;
                    }
                    p.docx_title {
                    margin-top: 24.00pt;
                    margin-bottom: 6.00pt;
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_title span {
                    font-weight: bold;
                    min-height: 36.00pt;
                    font-size: 36.00pt;
                    verticalAlign: -0.50pt;
                    }
                    p.docx_normal {
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_normal span {
                    verticalAlign: -0.50pt;
                    }
                    p.docx_heading1 {
                    line-height: calc(100% + 12pt);
                    text-align: center;
                    vertical-align: top;
                    }
                    p.docx_heading1 span {
                    font-family: Arial;
                    font-weight: bold;
                    verticalAlign: -0.50pt;
                    min-height: 12.00pt;
                    font-size: 12.00pt;
                    }
                    span.docx_defaultparagraphfont {
                    verticalAlign: -0.50pt;
                    }
                    table.docx_tablenormal p {
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    table.docx_tablenormal span {
                    verticalAlign: -0.50pt;
                    }
                    table.docx_tablenormal td {
                    text-align: left;
                    padding-top: 0.00pt;
                    padding-left: 5.40pt;
                    padding-bottom: 0.00pt;
                    padding-right: 5.40pt;
                    }
                    .docx_nolist p {
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_footer {
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_footer span {
                    verticalAlign: -0.50pt;
                    }
                    p.docx_bodytext {
                    line-height: calc(100% + 12pt);
                    text-align: center;
                    vertical-align: top;
                    }
                    p.docx_bodytext span {
                    font-family: Arial;
                    font-weight: bold;
                    verticalAlign: -0.50pt;
                    min-height: 11.00pt;
                    font-size: 11.00pt;
                    }
                    table.docx_tablegrid p {
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    table.docx_tablegrid span {
                    verticalAlign: -0.50pt;
                    }
                    table.docx_tablegrid td {
                    text-align: left;
                    border-top: 0.50pt solid black;
                    border-left: 0.50pt solid black;
                    border-bottom: 0.50pt solid black;
                    border-right: 0.50pt solid black;
                    padding-top: 0.00pt;
                    padding-left: 5.40pt;
                    padding-bottom: 0.00pt;
                    padding-right: 5.40pt;
                    }
                    p.docx_subtitle {
                    margin-top: 18.00pt;
                    margin-bottom: 4.00pt;
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    p.docx_subtitle span {
                    font-family: Georgia;
                    font-style: italic;
                    color: #666666;
                    min-height: 24.00pt;
                    font-size: 24.00pt;
                    verticalAlign: -0.50pt;
                    }
                    table.docx_table1 td {
                    padding-top: 0.00pt;
                    padding-left: 5.40pt;
                    padding-bottom: 0.00pt;
                    padding-right: 5.40pt;
                    text-align: left;
                    }
                    table.docx_table1 p {
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    table.docx_table1 span {
                    verticalAlign: -0.50pt;
                    }
                    table.docx_table2 td {
                    padding-top: 0.00pt;
                    padding-left: 5.40pt;
                    padding-bottom: 0.00pt;
                    padding-right: 5.40pt;
                    text-align: left;
                    }
                    table.docx_table2 p {
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    table.docx_table2 span {
                    verticalAlign: -0.50pt;
                    }
                    table.docx_table3 td {
                    padding-top: 0.00pt;
                    padding-left: 5.40pt;
                    padding-bottom: 0.00pt;
                    padding-right: 5.40pt;
                    text-align: left;
                    }
                    table.docx_table3 p {
                    line-height: calc(100% + 0.05pt);
                    vertical-align: top;
                    }
                    table.docx_table3 span {
                    verticalAlign: -0.50pt;
                    }
                </style><!--docxjs document numbering styles--><style>p.docx-num-1-0:before {
                    content: ""counter(docx-num-1-0, decimal)")\9";
                    counter-increment: docx-num-1-0;
                    font-weight: normal;
                    }
                    p.docx-num-1-0 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -192.75pt;
                    margin-left: 191.30pt;
                    }
                    p.docx-num-1-0 {
                    counter-reset: docx-num-1-1;
                    }
                    p.docx-num-1-1:before {
                    content: ""counter(docx-num-1-1, lower-alpha)".\9";
                    counter-increment: docx-num-1-1;
                    }
                    p.docx-num-1-1 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -18.00pt;
                    margin-left: 52.55pt;
                    }
                    p.docx-num-1-1 {
                    counter-reset: docx-num-1-2;
                    }
                    p.docx-num-1-2:before {
                    content: ""counter(docx-num-1-2, lower-roman)".\9";
                    counter-increment: docx-num-1-2;
                    }
                    p.docx-num-1-2 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -9.00pt;
                    margin-left: 88.55pt;
                    }
                    p.docx-num-1-2 {
                    counter-reset: docx-num-1-3;
                    }
                    p.docx-num-1-3:before {
                    content: ""counter(docx-num-1-3, decimal)".\9";
                    counter-increment: docx-num-1-3;
                    }
                    p.docx-num-1-3 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -18.00pt;
                    margin-left: 124.55pt;
                    }
                    p.docx-num-1-3 {
                    counter-reset: docx-num-1-4;
                    }
                    p.docx-num-1-4:before {
                    content: ""counter(docx-num-1-4, lower-alpha)".\9";
                    counter-increment: docx-num-1-4;
                    }
                    p.docx-num-1-4 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -18.00pt;
                    margin-left: 160.55pt;
                    }
                    p.docx-num-1-4 {
                    counter-reset: docx-num-1-5;
                    }
                    p.docx-num-1-5:before {
                    content: ""counter(docx-num-1-5, lower-roman)".\9";
                    counter-increment: docx-num-1-5;
                    }
                    p.docx-num-1-5 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -9.00pt;
                    margin-left: 196.55pt;
                    }
                    p.docx-num-1-5 {
                    counter-reset: docx-num-1-6;
                    }
                    p.docx-num-1-6:before {
                    content: ""counter(docx-num-1-6, decimal)".\9";
                    counter-increment: docx-num-1-6;
                    }
                    p.docx-num-1-6 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -18.00pt;
                    margin-left: 232.55pt;
                    }
                    p.docx-num-1-6 {
                    counter-reset: docx-num-1-7;
                    }
                    p.docx-num-1-7:before {
                    content: ""counter(docx-num-1-7, lower-alpha)".\9";
                    counter-increment: docx-num-1-7;
                    }
                    p.docx-num-1-7 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -18.00pt;
                    margin-left: 268.55pt;
                    }
                    p.docx-num-1-7 {
                    counter-reset: docx-num-1-8;
                    }
                    p.docx-num-1-8:before {
                    content: ""counter(docx-num-1-8, lower-roman)".\9";
                    counter-increment: docx-num-1-8;
                    }
                    p.docx-num-1-8 {
                    display: list-item;
                    list-style-position: inside;
                    list-style-type: none;
                    text-indent: -9.00pt;
                    margin-left: 304.55pt;
                    }
                    .docx-wrapper {
                    counter-reset: docx-num-1-0;
                    }
                </style>
            </head>
            <body>
                <div class="docx-wrapper">
                    <section class="docx" style="padding: 56.7pt; width: 595.35pt; min-height: 842pt;">
                        <article>
                            <table class="docx_table1" style="width: 492.75pt; table-layout: auto; margin-left: auto; margin-right: auto;">
                                <colgroup>
                                    <col style="width: 492.75pt;"/>
                                </colgroup>
                                <tr>
                                    <td style="vertical-align: top; border-width: 0.5pt; border-style: solid; border-color: rgb(0, 0, 0);">
                                        <p><span style="font-family: Calibri; font-weight: bold; min-height: 12pt; font-size: 12pt;">ЗАВОД ЗА ИНТЕЛЕКТУАЛНУ СВОЈИНУ, ОБРАЗАЦ А-1    </span></p>
                                        <p><span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">Београд, Кнегиње Љубице 5</span></p>
                                        <p style="border-width: initial; border-style: none; border-color: initial; background-color: inherit; margin: 0pt; line-height: 1; text-indent: 0pt; text-align: center;"><span style="font-family: Calibri; font-weight: bold; font-style: normal; text-transform: none; text-decoration: none; color: rgb(0, 0, 0); min-height: 12pt; font-size: 12pt; background-color: inherit;">ЗАХТЕВ ЗА УНОШЕЊЕ У ЕВИДЕНЦИЈУ И ДЕПОНОВАЊЕ АУТОРСКИХ ДЕЛА</span></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="vertical-align: top; border-width: 0.5pt; border-style: solid; border-color: rgb(0, 0, 0);">
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">1) Подносилац - име, презиме, адреса и држављанство аутора или другог носиоца ауторског права ако је подносилац физичко лице, односно пословно име и седиште носиоца ауторског права ако је подносилац правно лице</span>
                                            <span style="font-family: Arial; min-height: 12pt; font-size: 12pt;">*</span>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">:</span>
                                        </p>
                                        <xsl:choose>
                                            <xsl:when test="//a1:Applicant//user:TIndividual">
                                                <xsl:value-of select="//a1:Applicant/user:First_name"/>
                                                <xsl:text> </xsl:text>
                                                <xsl:value-of select="//a1:Applicant/user:Last_name"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Applicant/user:Citizenship"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Applicant
                                                /user:Address/user:Street"/>
                                                <xsl:text> </xsl:text>
                                                <xsl:value-of select="//a1:Applicant
                                                /user:Address/user:Street_number"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Applicant/user:Address/user:City"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Applicant/user:Address/user:Zip_code"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:value-of select="//a1:Applicant"/>
                                                <xsl:value-of select="//a1:Applicant
                                                /user:Address/user:Street"/>
                                                <xsl:text> </xsl:text>
                                                <xsl:value-of select="//a1:Applicant
                                                /user:Address/user:Street_number"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Applicant/user:Address/user:City"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Applicant/user:Address/user:Zip_code"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                        <br/>
                                        <table class="docx_table2" style="width: 490.5pt; text-align: left; table-layout: auto;">
                                            <colgroup>
                                                <col style="width: 256pt;"/>
                                                <col style="width: 234.5pt;"/>
                                            </colgroup>
                                            <tr style="height: 15.75pt;">
                                                <td style="vertical-align: top; border-width: 0.5pt; border-style: solid; border-color: rgb(0, 0, 0);">
                                                    <p><span style="font-family: Arial;">телефон:<span class="docx-tab-stop"></span></span><span><xsl:value-of select="//a1:Applicant/user:Phone_number"/></span></p>
                                                </td>
                                                <td style="vertical-align: top; border-width: 0.5pt; border-style: solid; border-color: rgb(0, 0, 0);">
                                                    <p><span style="font-family: Arial;">e-mail:</span><span><xsl:value-of select="//a1:Applicant/user:Email"/></span></p>
                                                </td>
                                            </tr>
                                        </table>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">2) Псеудоним или знак аутора, (ако га има):</span>
                                        </p>
                                        <xsl:if test="//a1:Author_pseudonym">
                                            <p><span>
                                                <xsl:value-of select="//a1:Author_pseudonym"/>
                                            </span></p>
                                        </xsl:if>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">3) Име, презиме и адреса пуномоћника, ако се пријава подноси преко пуномоћника:</span>
                                        </p>
                                        <p><span>
                                            <xsl:if test="//a1:Attorney">
                                                <xsl:value-of select="//a1:Attorney/user:First_name"/>
                                                <xsl:text> </xsl:text>
                                                <xsl:value-of select="//a1:Attorney/user:Last_name"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Attorney
                                                /user:Address/user:Street"/>
                                                <xsl:text> </xsl:text>
                                                <xsl:value-of select="//a1:Attorney
                                                /user:Address/user:Street_number"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Attorney/user:Address/user:City"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of select="//a1:Attorney/user:Address/user:Zip_code"/>
                                            </xsl:if>
                                        </span></p>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">4) Наслов ауторског дела, односно алтернативни наслов, ако га има, по коме ауторско дело може да се идентификује</span>
                                            <span style="font-family: Arial; min-height: 12pt; font-size: 12pt;">*</span>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">:</span>
                                        </p>
                                        <p><span><xsl:value-of select="//a1:Work_title/a1:Main_title"/></span></p>

                                        <p>
                                            <span>
                                            <xsl:if test="//a1:Work_title/a1:Alternative_title">
                                                <xsl:text> (</xsl:text>
                                                <xsl:value-of select="//a1:Work_title/a1:Alternative_title"/>
                                                <xsl:text>)</xsl:text>
                                            </xsl:if>
                                            </span>
                                        </p>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">5) Подаци о наслову ауторског дела на коме се заснива дело прераде, ако је у питању ауторско дело прераде, као и податак о аутору изворног дела:</span>
                                        </p>
                                        <p><span>
                                            <xsl:if test="//a1:Adaptation_work_information">
                                                <xsl:value-of
                                                        select="//a1:Adaptation_work_information/a1:Original_work_title"/>
                                                <xsl:text>, </xsl:text>
                                                <xsl:value-of
                                                        select="//a1:Adaptation_work_information/a1:Author/a1:Author_name"/>
                                                <xsl:text> </xsl:text>
                                                <xsl:value-of
                                                        select="//a1:Adaptation_work_information/a1:Author/a1:Author_surname"/>
                                                <xsl:choose>
                                                    <xsl:when
                                                            test="//a1:Adaptation_work_information/a1:Author/a1:Date_of_death">
                                                        <xsl:text>, </xsl:text>
                                                        <xsl:value-of
                                                                select="//a1:Adaptation_work_information/a1:Author/a1:Date_of_death"/>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <xsl:text/>
                                                    </xsl:otherwise>
                                                </xsl:choose>
                                            </xsl:if>
                                        </span></p>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">6) Подаци о врсти ауторског дела (књижевно дело, музичко дело, ликовно дело, рачунарски програм и др.)</span>
                                            <span style="font-family: Arial; min-height: 12pt; font-size: 12pt;"> *</span>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">:</span>
                                        </p>
                                        <p><span><xsl:value-of select="//a1:Work_type"/></span></p>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">7) Подаци о форми записа ауторског дела (штампани текст, оптички диск и слично)</span>
                                            <span style="font-family: Arial; min-height: 12pt; font-size: 12pt;"> *</span>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">:</span>
                                        </p>
                                        <p><span><xsl:value-of select="//a1:Form_of_recording_work"/></span></p>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">8) Подаци о аутору ако подносилац пријаве из тачке 1. овог захтева није аутор и то: презиме, име, адреса и држављанство аутора (групе аутора или коаутора), а ако су у питању један или више аутора који нису живи, имена аутора и године смрти аутора а ако је у питању ауторско дело анонимног аутора навод да је ауторско дело дело анонимног аутора: </span>
                                        </p>
                                        <xsl:if test="//a1:Author">
                                            <xsl:choose>
                                                <xsl:when test="//a1:Author/@anonymous = 'false'">
                                                    <p><span>
                                                        <xsl:value-of select="//a1:Author/a1:Author_name"/>
                                                        <xsl:text> </xsl:text>
                                                        <xsl:value-of select="//a1:Author/a1:Author_surname"/>
                                                        <xsl:if test="//a1:Author/a1:Citizenship">
                                                            <xsl:text>, </xsl:text>
                                                            <xsl:value-of select="//a1:Author/a1:Citizenship"/>
                                                        </xsl:if>
                                                        <xsl:if test="//a1:Author/a1:Date_of_death">
                                                            <xsl:text>, </xsl:text>
                                                            <xsl:value-of select="//a1:Author/a1:Date_of_death"/>
                                                        </xsl:if>
                                                    </span></p>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <xsl:text>Аутор је анониман.</xsl:text>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </xsl:if>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">9) Податак да ли је у питању ауторско дело створено у радном односу:</span>
                                        </p>
                                        <p><span>
                                            <xsl:choose>
                                                <xsl:when test="//a1:Work_made_in_business_relationship = 'true'">
                                                    <xsl:text>Ауторско дело је створено у радном односу.</xsl:text>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <xsl:text>Ауторско дело није створено у радном односу.</xsl:text>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </span></p>
                                        <p>
                                            <span style="font-family: Calibri; min-height: 12pt; font-size: 12pt;">10) Начин коришћења ауторског дела или намеравани начин коришћења ауторског дела:</span>
                                        </p>
                                        <xsl:if test="//a1:Way_of_using_work">
                                            <p><span>
                                                <xsl:value-of select="//a1:Way_of_using_work"/>
                                            </span></p>
                                        </xsl:if>

                                    </td>
                                </tr>
                            </table>
                            <table class="docx_table3" style="width: 492.75pt; text-align: left; table-layout: auto;">
                                <colgroup>
                                    <col style="width: 317.25pt;"/>
                                    <col style="width: 163.05pt;"/>
                                    <col style="width: 12.45pt;"/>
                                </colgroup>
                                <tr style="height: 49.8pt;">
                                    <td rowspan="2" style="border-top: 0.5pt solid rgb(0, 0, 0); border-left: 0.5pt solid rgb(0, 0, 0); border-right: 0.5pt solid rgb(0, 0, 0); vertical-align: top; border-bottom: 0.5pt solid rgb(0, 0, 0);">
                                    </td>
                                    <td style="border-width: 0.5pt; border-style: solid; border-color: rgb(0, 0, 0); vertical-align: top;">
                                        <p>
                                            <span style="font-family: Arial; min-height: 14pt; font-size: 14pt;">Број пријаве</span>
                                        </p>
                                        <p>
                                            <span style="font-family: Arial; font-weight: bold; min-height: 18pt; font-size: 18pt;"> <xsl:value-of select="//@request_number"/></span>

                                        </p>
                                    </td>
                                    <td rowspan="2" style="border-top: none; border-right: 0.5pt solid rgb(0, 0, 0); vertical-align: top; border-left: 0.5pt solid rgb(0, 0, 0); border-bottom: 0.5pt solid rgb(0, 0, 0);">
                                    </td>
                                </tr>
                                <tr style="height: 29.25pt;">
                                    <td style="display: none; border-top: none; border-left: 0.5pt solid rgb(0, 0, 0); border-right: 0.5pt solid rgb(0, 0, 0); vertical-align: top; border-bottom: 0.5pt solid rgb(0, 0, 0);">
                                    </td>
                                    <td style="border-width: 0.5pt; border-style: solid; border-color: rgb(0, 0, 0); vertical-align: top;">
                                        <p>
                                            <span style="font-family: Arial; min-height: 14pt; font-size: 14pt;">Датум подношења:</span>
                                        </p>
                                        <p><span><xsl:value-of select="//@request_submission_date"/></span></p>
                                    </td>
                                    <td style="display: none; border-top: none; border-right: 0.5pt solid rgb(0, 0, 0); vertical-align: top; border-left: 0.5pt solid rgb(0, 0, 0); border-bottom: 0.5pt solid rgb(0, 0, 0);">
                                    </td>
                                </tr>
                                <tr style="height: 11.25pt;">
                                    <td colspan="2" style="border-top: none; border-left: 0.5pt solid rgb(0, 0, 0); border-bottom: 0.5pt solid rgb(0, 0, 0); border-right: none; vertical-align: top;">
                                    </td>
                                    <td style="border-top: none; border-right: 0.5pt solid rgb(0, 0, 0); vertical-align: top; border-left: 0.5pt solid rgb(0, 0, 0); border-bottom: 0.5pt solid rgb(0, 0, 0);">
                                    </td>
                                </tr>
                            </table>
                            <p>
                                <span style="font-family: Arial; min-height: 12pt; font-size: 12pt;">Рубрике у захтеву А-1 које су означене са * морају да буду обавезно попуњене.</span>

                            </p>
                        </article>
                    </section>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>