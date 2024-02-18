package gvgulov.knowledgegraph.entity

import gvgulov.knowledgegraph.dto.PropertyDataDto
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