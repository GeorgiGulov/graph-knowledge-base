import {PropertyQueryDto} from "./PropertQueryDto";

export interface NodeQueryDto {
    id: string,
    label: string,
    properties: PropertyQueryDto[]
}