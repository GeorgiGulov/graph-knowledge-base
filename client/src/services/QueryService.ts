import {createApi, fetchBaseQuery} from '@reduxjs/toolkit/query/react'
import {GraphDataDto} from "../dto/graphDto/GraphDataDto";
import {GraphQueryDto} from "../dto/queryDto/GraphQueryDto";

export interface Pageable {
    size: number,
    numberPage: number
}

export interface QueryExecute {
    query: GraphQueryDto,
    pageable: Pageable
}

export const queryApi = createApi({
    reducerPath: "queryApi",
    baseQuery: fetchBaseQuery({
        baseUrl: "http://localhost:8080"
    }),
    endpoints: (build) => ({
        fetchGraphQuery: build.mutation<GraphDataDto, QueryExecute>({
            query: (queryGraph) => ({
                url: "/search",
                method: "POST",
                body: queryGraph
            })
        })
    })
})