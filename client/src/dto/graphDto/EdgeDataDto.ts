import {NodeDataDto} from "./NodeDataDto";
import {PropertyDataDto} from "./PropertDataDto";

export interface EdgeDataDto {
    id: string,
    label: string,
    properties: PropertyDataDto[],
    sourceNode: NodeDataDto,
    targetNode: NodeDataDto,
}