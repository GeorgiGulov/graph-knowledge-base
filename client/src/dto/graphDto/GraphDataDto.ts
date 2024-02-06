import {NodeDataDto} from "./NodeDataDto";
import {EdgeDataDto} from "./EdgeDataDto";


/**
 * Сущность данных, пришедшая от сервера, должна быть сконвертирована в сущность клиента
 */
export interface GraphDataDto {
    nodes: NodeDataDto[],
    edges: EdgeDataDto[]
}