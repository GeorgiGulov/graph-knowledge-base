package gvgulov.knowledgegraph.graphEntity

import gvgulov.knowledgegraph.dto.graph.PropertyDataDto
import java.util.*

data class PropertyData(
    val label: String,
    val value: Any,
    val id: String = UUID.randomUUID().toString(),
) {
    fun toDto() = PropertyDataDto(
        id = id,
        label = label,
        value = value.toString()
    )
}