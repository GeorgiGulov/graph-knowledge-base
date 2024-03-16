package gvgulov.knowledgegraph.database.entity

import gvgulov.knowledgegraph.database.annotation.AllOpen
import gvgulov.knowledgegraph.database.annotation.NoArguments
import jakarta.persistence.*

@Entity
@AllOpen
@NoArguments
@Table(name = "project")
class ProjectEntity(
    @Column(name = "project_name", nullable = false)
    val projectName: String,

    @Column(name = "uuid", nullable = false)
    val uuid: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)