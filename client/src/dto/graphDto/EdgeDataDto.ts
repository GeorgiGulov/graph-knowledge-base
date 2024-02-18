import {PropertyDataDto} from "./PropertDataDto";

export interface EdgeDataDto {
    id: string,
    label: string,
    properties: PropertyDataDto[],
    sourceNode: string,
    targetNode: string,
}