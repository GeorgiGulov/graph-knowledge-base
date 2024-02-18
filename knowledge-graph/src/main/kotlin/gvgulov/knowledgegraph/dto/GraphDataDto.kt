package gvgulov.knowledgegraph.dto

import gvgulov.knowledgegraph.entity.GraphData

data class GraphDataDto(
    val nodes: List<NodeDataDto>,
    val edges: List<EdgeDataDto>
) {
    fun toGraphData(): GraphData {
        val nodes = nodes.map { it.toNodeData() }
        val edges = edges.mapNotNull { it.toEdgeData(nodes = nodes) }

        return GraphData(
            nodes = nodes,
            edges = edges
        )
    }

}