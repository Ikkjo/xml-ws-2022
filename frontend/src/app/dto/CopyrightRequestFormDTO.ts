import { Individual, Person } from "./User";

export interface CopyrightRequestForm {
    applicant: Person;
    attorney: Individual;
    authorPseudonym: string;
    workTitle: WorkTitle;
    workType: string;
    formOfRecordingWork: string;
    adaptationWorkInfo: AdaptationWorkInfo;
    author: Author;
    isWorkMadeInBusinessRelationship: boolean;
    wayOfUsingWork: string;
    requestSubmissionDate: Date;
    requestNumber: number;
    institution: string;
    address: string;
}

export interface WorkTitle {
    title: string;
    alternateTitle: string
}

export interface Author {
    firstName: string;
    lastName: string;
    anonymous: boolean;

}

export interface AdaptationWorkInfo {
    originalWorkTitle: string;
    originalWorkAuthor: Author;
}
