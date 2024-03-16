package gvgulov.knowledgegraph.dto.graph

import gvgulov.knowledgegraph.graphEntity.PropertyData

data class PropertyDataDto(
    val id: String,
    val label: String,
    val value: String,
) {
    fun toPropertyData() = PropertyData(
        id = id,
        label = label,
        value = value
    )
}