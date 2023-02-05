import { OrganizationOfficial } from "./User";

export interface CopyrightSolutionDTO {
    requestNumber: string;
    accepted: boolean;
    official: OrganizationOfficial;
    solutionDate: Date;
    motivation: string;
}