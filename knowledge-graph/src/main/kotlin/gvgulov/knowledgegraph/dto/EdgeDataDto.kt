package gvgulov.knowledgegraph.dto

import gvgulov.knowledgegraph.entity.EdgeData
import java.util.*

data class EdgeDataDto(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val properties: List<PropertyDataDto>,
    val sourceNode: NodeDataDto,
    val targetNode: NodeDataDto,
) {
    fun toEdgeData() = EdgeData(
        id = id,
        label = label,
        properties = properties.map { it.toPropertyData() },
        source = sourceNode.toNodeData(),
        target = targetNode.toNodeData()
    )

}