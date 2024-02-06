import React, {useEffect, useState} from 'react';
import {Network} from "vis-network/peer/esm/vis-network";
import {DataSet} from "vis-data/peer/esm/vis-data";
import {Edge} from "vis-network/declarations/network/Network";
import {useAppDispatch, useAppSelector} from "../../hooks/redux";
import {graphSlice, IChangeElementLabel} from "../../store/reducers/GraphSlice";
import {nodeInfoSlice} from "../../store/reducers/NodeInfoSlice";
import MuRadioButton from "../UI/MuRadioButton";
import {INode} from "../../entity/graphQuery/INode";
import MyButton from "../UI/MyButton";
import {Pageable, queryApi, QueryExecute} from "../../services/QueryService";
import {GraphQueryDto} from "../../dto/queryDto/GraphQueryDto";
import MyInput from "../UI/MyInput";
import {NodeQueryDto} from "../../dto/queryDto/NodeQueryDto";
import {PropertyQueryDto} from "../../dto/queryDto/PropertQueryDto";
import {EdgeQueryDto} from "../../dto/queryDto/EdgeQueryDto";
import {GraphDataDto} from "../../dto/graphDto/GraphDataDto";
import {IProperty} from "../../entity/graphQuery/IProperty";
import {IEdge} from "../../entity/graphQuery/IEdge";
import {IGraph} from "../../entity/graphQuery/IGraph";

const Graph = () => {

    const addNode = graphSlice.actions.addNode
    const addEdge = graphSlice.actions.addEdge
    const deleteEdge = graphSlice.actions.deleteEdge
    const deleteNode = graphSlice.actions.deleteNode
    const setGraph = graphSlice.actions.setGraph

    const changeLabelNode = graphSlice.actions.changeLabelNode
    const changeLabelEdge = graphSlice.actions.changeLabelEdge


    const switchNodeInfo = nodeInfoSlice.actions.switchNodeInfo
    const listNodes = useAppSelector(state => state.graphReducer.nodes)
    const listEdges = useAppSelector(state => state.graphReducer.edges)

    const [labelElement, setLabelElement] = useState("")
    const [typeOperation, changeTypeOperation] = useState("none")
    const [network, setNetwork] = useState(null)


    const dispatch = useAppDispatch()
    const [getQuery] = queryApi.useFetchGraphQueryMutation()


    useEffect(() => {

        console.log("useEffectOnce")

        const nodes = new DataSet([
            ...listNodes
        ]);

        const edges = new DataSet([
            ...listEdges
        ]);

        // @ts-ignore
        const container: HTMLElement = document.getElementById("mynetwork");

        const data = {
            nodes,
            edges
        }

        const options = {
            width: "600px",
            height: "600px",
            nodes: {
                shape: 'dot'
            },
            edges: {
                smooth: false
            },
            interaction: {hover: true}
        }

        // @ts-ignore
        setNetwork(new Network(container, data, options))

    }, []);


    useEffect(() => {
        console.log("useEffect1", network)

        if (network != null) {
            console.log("useEffect2", network)
            const networkNew = network as Network

            const options = {
                width: "600px",
                height: "600px",
                nodes: {
                    shape: 'circle',
                    mass: 2,
                },
                edges: {
                    selectionWidth: 3,
                    font: {
                        align: "top"
                    },
                    smooth: true, //сгибающиеся или прямые ребра
                    arrows: {
                        to: {enabled: true, scaleFactor: 1, type: "arrow"}
                    }
                },
                interaction: {hover: true},
                manipulation: {
                    enabled: false,
                    // @ts-ignore
                    addNode: function (data, callback) {
                        console.log('add', data)

                        data.label = "Пустая вершина"

                        const node: INode = {
                            id: data.id,
                            label: data.label,
                            property: []

                        }
                        // @ts-ignore
                        dispatch(addNode(node))
                        callback(data)
                        networkNew.disableEditMode()
                    },
                    // @ts-ignore
                    editNode: function (data, callback) {
                        data.label = labelElement
                        callback(data)

                        const changeLabel: IChangeElementLabel = {
                            id: data.id,
                            label: labelElement
                        }

                        dispatch(changeLabelNode(changeLabel))
                        networkNew.disableEditMode()
                    },
                    // @ts-ignore
                    editEdge: function (data, callback) {
                        data.label = labelElement
                        callback(data)

                        const changeLabel: IChangeElementLabel = {
                            id: data.id,
                            label: labelElement
                        }

                        dispatch(changeLabelEdge(changeLabel))
                        networkNew.disableEditMode()
                    },
                    // @ts-ignore
                    addEdge: function (data, callback) {

                        const id = crypto.randomUUID().toString()
                        const edge: Edge = {
                            id: id,
                            from: data.from,
                            to: data.to
                        }

                        // @ts-ignore
                        dispatch(addEdge(edge))
                        data.id = id
                        callback(data)

                        networkNew.disableEditMode()
                    }
                },
            }

            networkNew.off('click')
            networkNew.on('click', event => {

                switch (typeOperation) {
                    case "none":
                        break

                    case "addSearch":
                        if (event.nodes.length == 0) {
                            networkNew.addNodeMode()
                        }
                        break

                    case "addData":
                        if (event.nodes.length == 0) {
                            networkNew.addNodeMode()
                        }
                        break
                    default:
                        break
                }


            })

            networkNew.off('selectEdge')
            networkNew.on("selectEdge", params => {
                console.log("selectEdge", typeOperation)

                switch (typeOperation) {
                    case "none":
                        break

                    case "editEdge":
                        console.log(params)
                        networkNew.editEdgeMode()
                        break

                    case "deleteEdge":
                        const edgeId: string = params.edges[0]
                        dispatch(deleteEdge(edgeId))
                        networkNew.deleteSelected()
                        break

                    default:
                        break

                }
            })

            networkNew.off('selectNode')
            networkNew.on("selectNode", params => {
                console.log("selectNode", typeOperation)

                switch (typeOperation) {
                    case "none":
                        const id = params.nodes[0]
                        dispatch(switchNodeInfo(id))
                        break

                    case "editNode":
                        networkNew.editNode()
                        break

                    case "addEdge":
                        networkNew.addEdgeMode()
                        break

                    case "deleteNode":
                        const nodeId: string = params.nodes[0]
                        const edgesId: string[] = params.edges

                        edgesId.forEach((edgeId) => {
                            dispatch(deleteEdge(edgeId))
                        })
                        dispatch(deleteNode(nodeId))
                        networkNew.deleteSelected()
                        break

                    default:
                        break
                }
            })

            networkNew.setOptions(options)

        }
    })

    {/*TODO: доделать запрос*/
    }
    return (
        <div>
            <MuRadioButton type={typeOperation} onChange={(str) => {
                changeTypeOperation(str)
            }}></MuRadioButton>


            <MyInput placeholder={"Имя вершины"} value={labelElement} onChange={(str) => {
                setLabelElement(str)
            }}/>


            <MyButton description={"Выполнить запрос"} onClick={async () => {
                const pageable: Pageable = {
                    size: 0,
                    numberPage: 1
                }

                const nodes: NodeQueryDto[] = listNodes.map((nodeReducer) => {
                    const node: NodeQueryDto = {
                        id: nodeReducer.id,
                        label: nodeReducer.label,
                        properties: nodeReducer.property.map((propertyReducer) => {
                            const propertyQueryDto: PropertyQueryDto = {
                                id: propertyReducer.id,
                                label: propertyReducer.label,
                                value: propertyReducer.value
                            }
                            return propertyQueryDto
                        })
                    }
                    return node
                })

                const edges = listEdges.map((edgeReducer) => {

                    const sourceNode = nodes.find((node) => node.id == edgeReducer.from)
                    const targetNode = nodes.find((node) => node.id == edgeReducer.to)

                    if (sourceNode != undefined && targetNode != undefined) {
                        const edge: EdgeQueryDto = {
                            id: edgeReducer.id,
                            label: edgeReducer.label,
                            properties: [],
                            sourceNode: sourceNode,
                            targetNode: targetNode
                        }

                        return edge
                    } else {
                        return null
                    }
                }).filter((edge) => edge !== null && edge !== undefined)

                const queryGraph: GraphQueryDto = {
                    nodes: nodes,
                    edges: edges as EdgeQueryDto[]
                }

                const query = {
                    query: queryGraph,
                    pageable: pageable
                } as QueryExecute

                let graphDto: GraphDataDto | null = null

                try {
                    const data = await getQuery(query)

                    // @ts-ignore
                    graphDto = data.data
                } catch (e) {
                    console.log("Ошибка при получении ответа на графовый запрос")
                }

                if (graphDto != null) {
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
                            from: edge.sourceNode.id,
                            to: edge.targetNode.id,
                        }
                        return edgeReducer
                    })


                    console.log(newNode)
                    console.log(newEdge)

                    const graph: IGraph = {
                        nodes: newNode,
                        edges: newEdge,
                    }

                    if (network != null) {

                        const nodes = new DataSet([
                            ...newNode
                        ]);

                        const edges = new DataSet([
                            ...newEdge
                        ]);

                        const data = {
                            nodes,
                            edges
                        }

                        const newNetwork = network as Network
                        newNetwork.setData(data)
                        dispatch(setGraph(graph))
                    }


                }

            }}/>

            <div id="mynetwork"></div>
        </div>

    );
};


export default Graph;