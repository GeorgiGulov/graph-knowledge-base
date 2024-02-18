import {combineReducers} from "redux";
import {configureStore} from "@reduxjs/toolkit";
import {graphReducer} from "./reducers/GraphSlice";
import {nodeInfoReducer} from "./reducers/NodeInfoSlice";

const rootReducer = combineReducers({
    graphReducer,
    nodeInfoReducer,
})

export const setupStore = () => {
    return configureStore({
        reducer: rootReducer,
    })
}

export type RootState = ReturnType<typeof rootReducer>
export type AppStore = ReturnType<typeof setupStore>
export type AppDispatch = AppStore['dispatch']

