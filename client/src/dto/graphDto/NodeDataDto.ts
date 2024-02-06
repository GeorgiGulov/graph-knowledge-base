import {PropertyDataDto} from "./PropertDataDto";

export interface NodeDataDto {
    id: string,
    label: string,
    properties: PropertyDataDto[]
}