package gvgulov.knowledgegraph.loader

import gvgulov.knowledgegraph.dao.GraphDao
import gvgulov.knowledgegraph.database.model.UserEntity
import gvgulov.knowledgegraph.entity.GraphData
import gvgulov.knowledgegraph.service.PopularityEntityService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val polarityService: PopularityEntityService,
    private val graphDao: GraphDao
) {
    fun saveGraph(graph: GraphData) {
        graph.nodes.forEach { node ->
            polarityService.incrementPopularity(node.label)
        }

        graphDao.saveGraph(newGraph = graph)
    }
}