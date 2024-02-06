import {EdgeQueryDto} from "./EdgeQueryDto";
import {NodeQueryDto} from "./NodeQueryDto";

/**
 * Сущность графового запроса, отправляемая на сервер (в дудущем может быть расширена предикатами)
 */
export interface GraphQueryDto {
    nodes: NodeQueryDto[],
    edges: EdgeQueryDto[]
}