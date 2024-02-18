package gvgulov.knowledgegraph.dto

import gvgulov.knowledgegraph.entity.NodeData
import java.util.*

data class NodeDataDto(
    val label: String,
    val properties: List<PropertyDataDto>,
    val id: String = UUID.randomUUID().toString(),
) {
    fun toNodeData() = NodeData(
        id = id,
        label = label,
        properties = properties.map { it.toPropertyData() }
    )
}