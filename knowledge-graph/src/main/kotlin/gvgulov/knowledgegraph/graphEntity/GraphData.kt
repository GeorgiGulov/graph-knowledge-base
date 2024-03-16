package gvgulov.knowledgegraph.graphEntity

import gvgulov.knowledgegraph.dto.GraphDataDto

data class GraphData(
    val nodes: List<NodeData> = listOf(),
    val edges: List<EdgeData> = listOf(),
) {
    fun toDto() = GraphDataDto(
        nodes = nodes.map { it.toDto() },
        edges = edges.map { it.toDto() }
    )
}