import {GraphDataDto} from "../graphDto/GraphDataDto";
import {executeGraph} from "../../services/QueryService";
import {INode} from "../../entity/graphQuery/INode";
import {IProperty} from "../../entity/graphQuery/IProperty";
import {IEdge} from "../../entity/graphQuery/IEdge";
import {IGraph} from "../../entity/graphQuery/IGraph";


export function dtoGraphDataToIGraph(graphDto: GraphDataDto) {
    console.log("Dto вершины ",graphDto.nodes)
    console.log("Dto ребра ",graphDto.edges)

    const newNode = graphDto.nodes.map((node) => {
        const nodeReducer: INode = {
            id: node.id,
            label: node.label,
            property: node.properties.map((propertyDto) => {
                const property: IProperty = {
                    id: propertyDto.id,
                    label: propertyDto.label,
                    value: propertyDto.value
                }
                return property
            })
        }
        return nodeReducer
    })

    const newEdge = graphDto.edges.map((edge) => {
        const edgeReducer: IEdge = {
            id: edge.id,
            label: edge.label,
            from: edge.sourceNode,
            to: edge.targetNode,
        }
        return edgeReducer
    })


    console.log("Новые вершины ",newNode)
    console.log("Новые ребра ",newEdge)

    const graph: IGraph = {
        nodes: newNode,
        edges: newEdge,
    }

    return graph
}