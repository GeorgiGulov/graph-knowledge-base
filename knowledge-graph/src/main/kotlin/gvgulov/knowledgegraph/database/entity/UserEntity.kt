package gvgulov.knowledgegraph.database.entity

import gvgulov.knowledgegraph.database.annotation.AllOpen
import gvgulov.knowledgegraph.database.annotation.NoArguments
import jakarta.persistence.*
@Entity
@AllOpen
@NoArguments
@Table(name = "person")
class UserEntity(
    @Column(name = "user_name", nullable = false)
    val username: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)