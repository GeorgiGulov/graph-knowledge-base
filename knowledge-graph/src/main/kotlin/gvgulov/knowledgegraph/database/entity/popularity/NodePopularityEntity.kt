package gvgulov.knowledgegraph.database.entity.popularity

import gvgulov.knowledgegraph.database.annotation.AllOpen
import gvgulov.knowledgegraph.database.annotation.NoArguments
import jakarta.persistence.*
@Entity
@AllOpen
@NoArguments
@Table(name = "node_popularity")
class NodePopularityEntity(
    @Id
    @Column(
        name = "label",
        nullable = false,
        unique = true
    )
    val label: String,

    @Column(name = "size", nullable = false)
    var size: Int,
)