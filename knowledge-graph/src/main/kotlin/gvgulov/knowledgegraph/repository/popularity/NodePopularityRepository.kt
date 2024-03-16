package gvgulov.knowledgegraph.repository.popularity

import gvgulov.knowledgegraph.database.entity.popularity.NodePopularityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NodePopularityRepository : JpaRepository<NodePopularityEntity, String> {
    fun findByOrderBySize(): List<NodePopularityEntity>
}