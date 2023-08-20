import { XMLBuilder, XMLParser } from "fast-xml-parser";
import {parseString, Parser, Builder, convertableToString} from 'xml2js';


export function objectToXML<T extends Object>(obj: T, rootElement: string): string {
    const builderOptions = {
        rootName: rootElement
    }
    let builder: Builder = new Builder(builderOptions);
    return builder.buildObject(obj);
}

export function xmlToObject(xmlString: string | convertableToString): any {

    const parserOptions = {
        explicitArray: false
    }

    let parser: Parser = new Parser(parserOptions);
    return parser.parseStringPromise(xmlString);
}
