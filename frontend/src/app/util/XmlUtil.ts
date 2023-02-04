const { XMLParser, XMLBuilder } = require("fast-xml-parser");

const options = {
    ignoreAttributes: false,
    attributeNamePrefix : "_",
    allowBooleanAttributes: true,
    ignoreDeclaration: true
}

const builder = new XMLBuilder(options);
const parser = new XMLParser(options);

export function xmlToObject<T extends Object>(xml: string): T{
    return builder.build(xml) as T;
}

export function objectToXML<T extends Object>(obj: T): string {
    return parser.parse(obj.toString());
}
