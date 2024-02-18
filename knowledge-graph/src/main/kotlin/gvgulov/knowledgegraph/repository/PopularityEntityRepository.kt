package gvgulov.knowledgegraph.repository

import gvgulov.knowledgegraph.database.model.PopularityEntity
import gvgulov.knowledgegraph.database.model.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PopularityEntityRepository : CrudRepository<PopularityEntity, Int> {
    fun findByLabel(label: String): Optional<PopularityEntity>


}