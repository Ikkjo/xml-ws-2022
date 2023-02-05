import {Person, Individual, Address} from "./User";

export interface RequestForPatentRecognitionDTO {

  informationForInstitution: InformationForInstitution;
  patentName: PatentName;
  applicant: Person;
  inventor: Inventor;
  proxy: Proxy;
  deliveryAddress: DeliveryAddress;
  deliveryType: DeliveryType;
  applicationInformation: ApplicationInformation;
  priorityRightsRecognitionFromEarlierApplications: PriorityRightsRecognitionFromEarlierApplications;
  address: string;
  institution: string;
  isAccepted: boolean;
}

export interface InformationForInstitution {
  applicationNumber: string;
  receiptDate: string;
  submissionDate: string;
}

export interface PatentName {
  serbianPatentName: string;
  englishPatentName: string;
}

export interface DeliveryAddress extends Address{}

export interface DeliveryType {
  electronicDelivery: boolean;
  deliveryInPaperForm: boolean;
}

export interface ApplicationInformation {
  originalApplicationNumber: string;
  originalApplicationSubmissionDate: string;
  supplementaryApplication: boolean;
  separateApplication: boolean;
}

export interface PriorityRightsRecognitionFromEarlierApplications {
  earlierApplications: EarlierApplications;
}

export interface EarlierApplications {
  earlierApplication: EarlierApplication[];
}

export interface EarlierApplication {
  earlierApplicationSubmissionDate: string;
  earlierApplicationNumber: string;
  countryOrOrganizationDesignation: string;
}

export interface Inventor extends Individual {
  doesNotWantToBeListed: boolean;
}

export interface Proxy extends Individual {
  proxyForRepresentation: boolean;
  attorneyForReceivingLetters: boolean;
}
