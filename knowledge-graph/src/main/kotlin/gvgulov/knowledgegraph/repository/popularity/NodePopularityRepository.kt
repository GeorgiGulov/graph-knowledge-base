package gvgulov.knowledgegraph.repository.popularity

import gvgulov.knowledgegraph.database.entity.popularity.NodePopularityEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NodePopularityRepository : CrudRepository<NodePopularityEntity, String>