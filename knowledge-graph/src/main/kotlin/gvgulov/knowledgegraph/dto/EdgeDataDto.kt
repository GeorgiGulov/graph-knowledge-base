package gvgulov.knowledgegraph.dto

import gvgulov.knowledgegraph.graphEntity.EdgeData
import gvgulov.knowledgegraph.graphEntity.NodeData
import gvgulov.knowledgegraph.utils.getLogger
import java.util.*

data class EdgeDataDto(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val properties: List<PropertyDataDto>,
    val sourceNode: String,
    val targetNode: String,
) {
    fun toEdgeData(
        nodes: List<NodeData>
    ): EdgeData? =
        try {
            convert(nodes)
        } catch (e: Exception) {
            getLogger().error(e.message)
            null
        }

    private fun convert(
        nodes: List<NodeData>
    ): EdgeData {
        val sourceNode = nodes.firstOrNull {
            it.id == sourceNode
        } ?: error("failed calculate source node")

        val targetNode = nodes.firstOrNull {
            it.id == targetNode
        } ?: error("failed calculate target node")

        return EdgeData(
            id = id,
            label = label,
            properties = properties.map { it.toPropertyData() },
            source = sourceNode,
            target = targetNode
        )
    }

}