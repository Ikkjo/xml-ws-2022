import { Individual, LegalEntity, Person } from "../User";

export interface CopyrightRequest {
    applicantIndividual: Individual;
    applicantLegalEntity: LegalEntity;
    attorney: Individual;
    authorPseudonym?: string;
    workTitle: WorkTitle;
    workType: string;
    formOfRecordingWork: string;
    adaptationWorkInfo?: AdaptationWorkInfo;
    author?: Author;
    isWorkMadeInBusinessRelationship: boolean;
    wayOfUsingWork: string;
    requestSubmissionDate: Date | number | string;
    requestNumber?: number;
    institution?: string;
    address?: string;
    accepted: boolean;
}

export interface WorkTitle {
    title: string;
    alternateTitle: string
}

export interface Author {
    firstName: string;
    lastName: string;
    anonymous?: boolean;
    dateOfDeath?: Date;
}

export interface AdaptationWorkInfo {
    originalWorkTitle: string;
    originalWorkAuthor: Author;
}
