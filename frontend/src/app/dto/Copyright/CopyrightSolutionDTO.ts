import { OrganizationOfficial } from "../User";

export interface CopyrightSolution {
    requestNumber: string;
    accepted: boolean;
    official: OrganizationOfficial;
    solutionDate: Date;
    motivation: string;
}