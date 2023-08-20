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
      applicant: {
        mustBeBefore: ['inventor', 'proxy', 'deliveryAddress', 'deliveryType', 'applicationInformation', 'earlierApplications'],
        menu: [
          {
            caption: 'Add first name',
            action: Xonomy.newElementChild,
            actionParameter: '<FirstName></FirstName>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("firstName");
            }
          },
          {
            caption: 'Add last name',
            action: Xonomy.newElementChild,
            actionParameter: '<LastName></LastName>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("lastName");
            }
          },
          {
            caption: 'Add phone number',
            action: Xonomy.newElementChild,
            actionParameter: '<PhoneNumber></PhoneNumber>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("phoneNumber");
            }
          },
          {
            caption: 'Add email',
            action: Xonomy.newElementChild,
            actionParameter: '<Email></Email>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("email");
            }
          },
          {
            caption: 'Add fax number',
            action: Xonomy.newElementChild,
            actionParameter: '<FaxNumber></FaxNumber>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("faxNumber");
            }
          },
          {
            caption: 'Add address',
            action: Xonomy.newElementChild,
            actionParameter: '<Address></Address>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("address");
            }
          },
          {
            caption: 'Add citizenship',
            action: Xonomy.newElementChild,
            actionParameter: '<Citizenship></Citizenship>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("citizenship");
            }
          },
        ],
        attributes: {},
      },
      firstName: {
        mustBeBefore: ['lastName', 'phoneNumber', 'email', 'faxNumber', 'address', 'citizenship'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      lastName: {
        mustBeBefore: ['phoneNumber', 'email', 'faxNumber', 'address', 'citizenship'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      phoneNumber: {
        mustBeBefore: ['email', 'faxNumber', 'address', 'citizenship'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      email: {
        mustBeBefore: ['faxNumber', 'address', 'citizenship'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      faxNumber: {
        mustBeBefore: ['address', 'citizenship'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      address: {
        mustBeBefore: ['citizenship'],
        menu: [
          {
            caption: 'Add street',
            action: Xonomy.newElementChild,
            actionParameter: '<Street></Street>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("street");
            }
          },
          {
            caption: 'Add street number',
            action: Xonomy.newElementChild,
            actionParameter: '<StreetNumber></StreetNumber>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("streetNumber");
            }
          },
          {
            caption: 'Add city',
            action: Xonomy.newElementChild,
            actionParameter: '<City></City>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("city");
            }
          },
          {
            caption: 'Add zip code',
            action: Xonomy.newElementChild,
            actionParameter: '<ZipCode></ZipCode>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("zipCode");
            }
          },
          {
            caption: 'Add country',
            action: Xonomy.newElementChild,
            actionParameter: '<Country></Country>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("country");
            }
          }
        ]
      },
      street: {
        mustBeBefore: ['streetNumber', 'city', 'zipCode', 'country'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      streetNumber: {
        mustBeBefore: ['city', 'zipCode', 'country'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      city: {
        mustBeBefore: ['zipCode', 'country'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      zipCode: {
        mustBeBefore: ['country'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      country: {
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      citizenship: {
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      inventor: {
        mustBeBefore: ['proxy', 'deliveryAddress', 'deliveryType', 'applicationInformation', 'earlierApplications'],
        menu: [
          {
            caption: 'Add first name',
            action: Xonomy.newElementChild,
            actionParameter: '<FirstName></FirstName>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("firstName");
            }
          },
          {
            caption: 'Add last name',
            action: Xonomy.newElementChild,
            actionParameter: '<LastName></LastName>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("lastName");
            }
          },
          {
            caption: 'Add phone number',
            action: Xonomy.newElementChild,
            actionParameter: '<PhoneNumber></PhoneNumber>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("phoneNumber");
            }
          },
          {
            caption: 'Add email',
            action: Xonomy.newElementChild,
            actionParameter: '<Email></Email>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("email");
            }
          },
          {
            caption: 'Add fax number',
            action: Xonomy.newElementChild,
            actionParameter: '<FaxNumber></FaxNumber>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("faxNumber");
            }
          },
          {
            caption: 'Add address',
            action: Xonomy.newElementChild,
            actionParameter: '<Address></Address>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("address");
            }
          },
          {
            caption: 'Add if inventor want to be listed',
            action: Xonomy.newAttribute,
            actionParameter: {name:'doesNotWantToBeListed', value: 'true'},
            hideIf:function(jsElement: any) {
              return jsElement.hasAttribute("doesNotWantToBeListed");
            }
          }
        ],
        attributes: {
          doesNotWantToBeListed: {
            asker: Xonomy.askPicklist,
            askerParameter: ["true", "false"]
          }
        }
      },
      proxy: {
        mustBeBefore: ['deliveryAddress', 'deliveryType', 'applicationInformation', 'earlierApplications'],
        menu: [
          {
            caption: 'Add first name',
            action: Xonomy.newElementChild,
            actionParameter: '<FirstName></FirstName>',
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("firstName");
            }
          },
          {
            caption: 'Add last name',
            action: Xonomy.newElementChild,
            actionParameter: '<LastName></LastName>',
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("lastName");
            }
          },
          {
            caption: 'Add phone number',
            action: Xonomy.newElementChild,
            actionParameter: '<PhoneNumber></PhoneNumber>',
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("phoneNumber");
            }
          },
          {
            caption: 'Add email',
            action: Xonomy.newElementChild,
            actionParameter: '<Email></Email>',
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("email");
            }
          },
          {
            caption: 'Add fax number',
            action: Xonomy.newElementChild,
            actionParameter: '<FaxNumber></FaxNumber>',
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("faxNumber");
            }
          },
          {
            caption: 'Add address',
            action: Xonomy.newElementChild,
            actionParameter: '<Address></Address>',
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("address");
            }
          },
          {
            caption: 'Add if proxy is for representation',
            action: Xonomy.newAttribute,
            actionParameter: {name: 'proxyForRepresentation', value: 'true'},
            hideIf: function (jsElement: any) {
              return jsElement.hasAttribute("proxyForRepresentation");
            }
          },
          {
            caption: 'Add if proxy is for receiving letters',
            action: Xonomy.newAttribute,
            actionParameter: {name: 'proxyForReceivingLetters', value: 'true'},
            hideIf: function (jsElement: any) {
              return jsElement.hasAttribute("proxyForReceivingLetters");
            }
          }
        ],
        attributes: {
          proxyForRepresentation: {
            asker: Xonomy.askPicklist,
            askerParameter: ["true", "false"]
          },
          proxyForReceivingLetters: {
            asker: Xonomy.askPicklist,
            askerParameter: ["true", "false"]
          }
        }
      },
      deliveryAddress: {
        mustBeBefore: ['deliveryType', 'applicationInformation', 'earlierApplications'],
        menu: [
          {
            caption: 'Add address',
            action: Xonomy.newElementChild,
            actionParameter: '<Address></Address>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("address");
            }
          }
        ]
      },
      deliveryType: {
        mustBeBefore: ['applicationInformation', 'earlierApplications'],
        menu: [
          {
            caption: 'Add consent for delivery in electronic form',
            action: Xonomy.newElementChild,
            actionParameter: '<ElectronicDelivery></ElectronicDelivery>',
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("electronicDelivery");
            },
          },
          {
            caption: 'Add consent for delivery in paper form',
            action: Xonomy.newElementChild,
            actionParameter: '<DeliveryInPaperForm></DeliveryInPaperForm>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("deliveryInPaperForm");
            },
          }
        ]
      },
      electronicDelivery: {
        mustBeBefore: ['deliveryInPaperForm'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askPicklist,
        askerParameter:["true", "false"]
      },
      deliveryInPaperForm: {
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askPicklist,
        askerParameter:["true", "false"]
      },
      applicationInformation: {
        mustBeBefore: ['earlierApplications'],
        menu: [
          {
            caption: 'Add original application number',
            action: Xonomy.newElementChild,
            actionParameter: '<OriginalApplicationNumber></OriginalApplicationNumber>',
            hideIf:function(jsElement: any) {
              return !jsElement.hasChildElement("originalApplicationNumber");
            }
          },
          {
            caption: 'Add original application submission date',
            action: Xonomy.newElementChild,
            actionParameter: '<OriginalApplicationSubmissionDate></OriginalApplicationSubmissionDate>',
            hideIf:function(jsElement: any) {
              return !jsElement.hasChildElement("originalApplicationSubmissionDate");
            }
          },
          {
            caption: 'Supplementary application',
            action: Xonomy.newAttribute,
            actionParameter: {name: 'supplementaryApplication', value: 'true'},
            hideIf:function(jsElement: any) {
              return !jsElement.hasAttribute("supplementaryApplication");
            }
          },
          {
            caption: 'Separate application',
            action: Xonomy.newAttribute,
            actionParameter: {name: 'separateApplication', value: 'true'},
            hideIf:function(jsElement: any) {
              return !jsElement.hasAttribute("separateApplication");
            }
          },
        ],
        attributes: {
          supplementaryApplication: {
            asker: Xonomy.askPicklist,
            askerParameter: ["true", "false"]
          },
          separateApplication: {
            asker: Xonomy.askPicklist,
            askerParameter: ["true", "false"]
          }
        }
      },
      originalApplicationNumber: {
        mustBeBefore: ['separateApplication'],
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString,
      },
      originalApplicationSubmissionDate: {
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString,
      },
      priorityRightsRecognitionFromEarlierApplications: {
        menu:[
          {
            caption: 'Add earlier applications',
            action: Xonomy.newElementChild,
            actionParameter: '<EarlierApplications></EarlierApplications>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("earlierApplications");
            }
          }
        ]
      },
      earlierApplications: {
        menu: [
          {
            caption: 'Add earlier application',
            action: Xonomy.newElementChild,
            actionParameter: '<EarlierApplication></EarlierApplication>',
          }
        ]
      },
      earlierApplication: {
        menu: [
          {
            caption: 'Add earlier application submission date',
            action: Xonomy.newElementChild,
            actionParameter: '<EarlierSubmissionDate></EarlierSubmissionDate>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("earlierSubmissionDate");
            }
          },
          {
            caption: 'Add earlier application number',
            action: Xonomy.newElementChild,
            actionParameter: '<EarlierApplicationNumber></EarlierApplicationNumber>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("earlierApplicationNumber");
            }
          },
          {
            caption: 'Add country or organization designation',
            action: Xonomy.newElementChild,
            actionParameter: '<CountryOrOrganizationDesignation></CountryOrOrganizationDesignation>',
            hideIf:function(jsElement: any) {
              return jsElement.hasChildElement("countryOrOrganizationDesignation");
            }
          }
        ]
      },
      earlierApplicationSubmissionDate: {
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      earlierApplicationNumber: {
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      },
      countryOrOrganizationDesignation: {
        hasText:true,
        oneLiner:true,
        asker: Xonomy.askString
      }
    }
  }
}
