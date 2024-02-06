import {createSlice, PayloadAction} from "@reduxjs/toolkit";

interface NodeInfoState {
    nodeInfoId: string
}

const initState: NodeInfoState = {
    nodeInfoId: "0"
}

export const nodeInfoSlice = createSlice({
    name: 'nodeInfo',
    initialState: initState,
    reducers: {
        switchNodeInfo(state, action: PayloadAction<string>) {
            state.nodeInfoId = action.payload
        },
    }
})

export const nodeInfoReducer = nodeInfoSlice.reducer