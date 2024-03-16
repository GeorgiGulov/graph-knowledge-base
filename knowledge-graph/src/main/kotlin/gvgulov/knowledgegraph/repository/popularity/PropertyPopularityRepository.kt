package gvgulov.knowledgegraph.repository.popularity

import gvgulov.knowledgegraph.database.entity.popularity.PropertyPopularityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PropertyPopularityRepository : JpaRepository<PropertyPopularityEntity, String>