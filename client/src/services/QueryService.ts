import {GraphQueryDto} from "../dto/queryDto/GraphQueryDto";
import {GraphDataDto} from "../dto/graphDto/GraphDataDto";

export interface Pageable {
    size: number,
    numberPage: number
}

export const executeGraph = async (
    query: GraphQueryDto,
    pageable: Pageable
) => {
    return fetch('http://localhost:8080/search', {
        method: "post",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(query)
    }).then((responce) =>
        responce.json() as unknown as GraphDataDto
    )
}

export const getAllGraphData = async () => {
    return fetch('http://localhost:8080/allData', {
        method: "get",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
    }).then((responce) =>
        responce.json() as unknown as GraphDataDto
    )
}