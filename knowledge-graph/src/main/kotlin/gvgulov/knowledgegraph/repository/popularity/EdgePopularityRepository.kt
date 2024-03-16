package gvgulov.knowledgegraph.repository.popularity

import gvgulov.knowledgegraph.database.entity.popularity.EdgePopularityEntity
import gvgulov.knowledgegraph.database.entity.popularity.NodePopularityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EdgePopularityRepository : JpaRepository<EdgePopularityEntity, String> {
    fun findByOrderBySize(): List<EdgePopularityEntity>

}