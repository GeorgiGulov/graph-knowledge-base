import {PropertyQueryDto} from "./PropertQueryDto";
import {NodeQueryDto} from "./NodeQueryDto";


export interface EdgeQueryDto {
    id: string,
    label: string,
    properties: PropertyQueryDto[],
    sourceNode: NodeQueryDto,
    targetNode: NodeQueryDto,
}