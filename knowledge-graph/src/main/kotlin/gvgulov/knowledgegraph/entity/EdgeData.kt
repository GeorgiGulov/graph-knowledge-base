package gvgulov.knowledgegraph.entity

import gvgulov.knowledgegraph.dto.EdgeDataDto
import java.util.*

data class EdgeData(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val properties: List<PropertyData>,
    val source: NodeData,
    val target: NodeData,
) {

    fun toDto() = EdgeDataDto(
        id = id,
        label = label,
        properties = properties.map { it.toDto() },
        sourceNode = source.toDto(),
        targetNode = target.toDto()
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