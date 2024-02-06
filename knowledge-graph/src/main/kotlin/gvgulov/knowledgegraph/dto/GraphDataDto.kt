package gvgulov.knowledgegraph.dto

import gvgulov.knowledgegraph.entity.GraphData

data class GraphDataDto(
    val nodes: List<NodeDataDto>,
    val edges: List<EdgeDataDto>
) {
    fun toGraphData() = GraphData(
        nodes = nodes.map { it.toNodeData() },
        edges = edges.map { it.toEdgeData() }
    )
}