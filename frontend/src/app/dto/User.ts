interface User {
    email: string;
    phoneNumber: string;
    faxNumber: string;
}

export interface Person extends User {
    address: Address;
}

export interface Address {
    street: string;
    streetNumber: number;
    city: string;
    zip: string;
}

export interface LegalEntity extends Person {
    businessName: string;
}

export interface Individual extends Person {
    firstName: string;
    lastName: string;
}

export interface OrganizationOfficial extends User {
    firstName: string;
    lastName: string;
}