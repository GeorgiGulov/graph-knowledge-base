import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {INode} from "../../entity/graphQuery/INode";
import {IEdge} from "../../entity/graphQuery/IEdge";
import {IGraph} from "../../entity/graphQuery/IGraph";


const initState: IGraph = {
    nodes: [
        {
            id: "1",
            label: 'Студент',
            property: [
                {
                    id: "1",
                    label: "ФИО",
                    value: "Иванов Иван Иваныч"
                }
            ]
        },
        {
            id: "2",
            label: 'Предмет',
            property: []
        }
    ],
    edges: [
        {id: "1", label: "изучает", from: "1", to: "2",}
    ]
}


export interface IChangeElementLabel {
    id: string,
    label: string
}

export const graphSlice = createSlice({
    name: 'queryGraph',
    initialState: initState,
    reducers: {
        setGraph(state, action: PayloadAction<IGraph>) {
            state.nodes = action.payload.nodes
            state.edges = action.payload.edges

        },

        addNode(state, action: PayloadAction<INode>) {
            state.nodes = state.nodes.concat(action.payload)

        },
        changeLabelNode(state, action: PayloadAction<IChangeElementLabel>) {
            state.nodes.map((node)=>{
                if(node.id == action.payload.id) {
                    node.label = action.payload.label
                }
                return node
            })
        },
        changeLabelEdge(state, action: PayloadAction<IChangeElementLabel>) {

            console.log(action.payload)

            state.edges.map((edge)=>{
                console.log(edge)

                if(edge.id == action.payload.id) {
                    edge.label = action.payload.label
                }
                return edge
            })
            console.log(state.edges)
            return state
        },
        changeNode(state, action: PayloadAction<INode>) {
            const newNode = action.payload
            state.nodes = [...state.nodes.filter((it) => it.id != newNode.id)].concat(newNode)
        },
        addEdge(state, action: PayloadAction<IEdge>) {
            console.log("addEdge", action.payload)
            state.edges = state.edges.concat(action.payload)

        },
        deleteNode(state, action: PayloadAction<string>) {
            state.nodes = state.nodes.filter((node)=> node.id != action.payload)

        },
        deleteEdge(state, action: PayloadAction<string>) {
            state.edges = state.edges.filter((edge)=> edge.id != action.payload)

        }
    }
})

export const graphReducer = graphSlice.reducer