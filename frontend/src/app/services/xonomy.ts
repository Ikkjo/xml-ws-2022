import { Injectable } from '@angular/core';

declare const Xonomy:any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() {}

  public requestForPatentRecognitionSpecification = {
    elements: {
      requestForPatentRecognition: {
        menu:[
          {
            caption: 'Add name',
            action: Xonomy.newElementChild,
            actionParameter: '<PatentName></PatentName>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("patentName");
            }
          },
          {
            caption: 'Add applicant',
            action: Xonomy.newElementChild,
            actionParameter: '<Applicant></Applicant>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("applicant");
            }
          },
          {
            caption: 'Add inventor',
            action: Xonomy.newElementChild,
            actionParameter: '<Inventor></Inventor>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("inventor");
            }
          },
          {
            caption: 'Add proxy',
            action: Xonomy.newElementChild,
            actionParameter: '<Proxy></Proxy>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("proxy");
            }
          },
          {
            caption: 'Add delivery address',
            action: Xonomy.newElementChild,
            actionParameter: '<DeliveryAddress></DeliveryAddress>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("deliveryAddress");
            }
          },
          {
            caption: 'Add delivery type',
            action: Xonomy.newElementChild,
            actionParameter: '<DeliveryType></DeliveryType>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("deliveryType");
            }
          },
          {
            caption: 'Add application information',
            action: Xonomy.newElementChild,
            actionParameter: '<ApplicationInformayion></ApplicationInformayion>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("applicationInformation");
            }
          },
          {
            caption: 'Add priority rights recognition from earlier applications',
            action: Xonomy.newElementChild,
            actionParameter: '<PriorityRightsRecognitionFromEarlierApplications></ApplicationInformayion>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("earlierApplications");
            }
          },
        ],
        attributes:{}
      },
      patentName: {
        mustBeBefore: ['applicant', 'inventor', 'proxy', 'deliveryAddress', 'deliveryType', 'applicationInformation', 'earlierApplications'],
          menu:[{
            caption: 'Add serbian name',
            action: Xonomy.newElementChild,
            actionParameter: '<SerbianPatentName></SerbianPatentName>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("serbianPatentName");
            }
          },
          {
            caption: 'Add english name',
            action: Xonomy.newElementChild,
            actionParameter: '<EnglishPatentName></EnglishPatentName>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("englishPatentName");
            }
          }
        ]
      },
      serbianPatentName: {
        mustBeBefore: ['englishPatentName'],
        hasText: true,
        oneLiner: true,
        asker: Xonomy.askString
      },
      englishPatentName: {
        hasText: true,
        oneLiner: true,
        asker: Xonomy.askString
      },
      applicant: {}
    }
  }
}
