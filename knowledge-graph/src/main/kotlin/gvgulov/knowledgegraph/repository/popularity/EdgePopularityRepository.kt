package gvgulov.knowledgegraph.repository.popularity

import gvgulov.knowledgegraph.database.entity.popularity.EdgePopularityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EdgePopularityRepository : JpaRepository<EdgePopularityEntity, String>