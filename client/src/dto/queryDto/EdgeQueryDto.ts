import {PropertyQueryDto} from "./PropertQueryDto";


export interface EdgeQueryDto {
    id: string,
    label: string,
    properties: PropertyQueryDto[],
    sourceNode: string,
    targetNode: string,
}