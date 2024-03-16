package gvgulov.knowledgegraph.repository.user

import gvgulov.knowledgegraph.database.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : CrudRepository<UserEntity, Int> {
    fun findByUsername(userName: String): Optional<UserEntity>
}