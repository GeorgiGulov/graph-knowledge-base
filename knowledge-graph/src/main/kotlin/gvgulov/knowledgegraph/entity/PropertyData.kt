package gvgulov.knowledgegraph.entity

import gvgulov.knowledgegraph.dto.PropertyDataDto

data class PropertyData(
     val label: String,
     val value: Any,
     val id: String,
) {

    fun toDto() = PropertyDataDto(
        id = id,
        label = label,
        value = value.toString()
    )
}