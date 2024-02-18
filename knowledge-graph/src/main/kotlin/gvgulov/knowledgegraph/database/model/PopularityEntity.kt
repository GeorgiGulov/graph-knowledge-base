package gvgulov.knowledgegraph.database.model

import gvgulov.knowledgegraph.database.annotation.AllOpen
import gvgulov.knowledgegraph.database.annotation.NoArguments
import jakarta.persistence.*
@Entity
@AllOpen
@NoArguments
@Table(name = "popularity")
class PopularityEntity(
    @Column(name = "label", nullable = false, unique = true)
    val label: String,

    @Column(name = "size", nullable = false)
    val size: Int,

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {
    fun copy(
        label: String = this.label,
        size: Int = this.size,
        id: Int? = this.id
    ) = PopularityEntity(
        label = label,
        size = size,
        id = id
    )
}