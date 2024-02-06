import {combineReducers} from "redux";
import {configureStore} from "@reduxjs/toolkit";
import {graphReducer} from "./reducers/GraphSlice";
import {nodeInfoReducer} from "./reducers/NodeInfoSlice";
import {queryApi} from "../services/QueryService";

const rootReducer = combineReducers({
    graphReducer,
    nodeInfoReducer,
    [queryApi.reducerPath]: queryApi.reducer
})

export const setupStore = () => {
    return configureStore({
        reducer: rootReducer,
        middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(queryApi.middleware)
    })
}

export type RootState = ReturnType<typeof rootReducer>
export type AppStore = ReturnType<typeof setupStore>
export type AppDispatch = AppStore['dispatch']

