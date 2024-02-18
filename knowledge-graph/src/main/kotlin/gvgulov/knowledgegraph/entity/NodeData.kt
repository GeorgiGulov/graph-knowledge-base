package gvgulov.knowledgegraph.entity

import gvgulov.knowledgegraph.dto.NodeDataDto
import java.util.*

data class NodeData(
     val label: String,
     val properties: List<PropertyData>,
     val id: String = UUID.randomUUID().toString(),
)  {

    fun toDto() = NodeDataDto(
        id = id,
        label = label,
        properties = properties.map { it.toDto() }
    )

    override fun toString(): String = """
    NodeData = (
        id = $id,
        label = $label,
        properties = [$properties],
    )
    """.trimIndent()

}