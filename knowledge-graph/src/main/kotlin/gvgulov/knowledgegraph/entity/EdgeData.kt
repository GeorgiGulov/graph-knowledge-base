package gvgulov.knowledgegraph.entity

import gvgulov.knowledgegraph.dto.EdgeDataDto
import java.util.*

data class EdgeData(
    val label: String,
    val source: NodeData,
    val target: NodeData,
    val properties: List<PropertyData> = emptyList(),
    val id: String = UUID.randomUUID().toString(),
) {

    fun toDto() = EdgeDataDto(
        id = id,
        label = label,
        properties = properties.map { it.toDto() },
        sourceNode = source.id,
        targetNode = target.id
    )

    override fun toString(): String = """
    EdgeData = (
        id = $id,
        label = $label,
        properties = [$properties],
        source = [id = ${source.id}, label = ${source.label}],
        target = [id = ${target.id}, label = ${target.label}]
    )"
    """.trimIndent()

}