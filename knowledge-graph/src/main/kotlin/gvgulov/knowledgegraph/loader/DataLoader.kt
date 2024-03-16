package gvgulov.knowledgegraph.loader

import gvgulov.knowledgegraph.dao.GraphDao
import gvgulov.knowledgegraph.graphEntity.GraphData
import gvgulov.knowledgegraph.service.PopularityEntityService
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val polarityService: PopularityEntityService,
    private val graphDao: GraphDao
) {
    fun saveGraph(graph: GraphData) {
        polarityService.incrementPopularity(graph)
        graphDao.saveGraph(newGraph = graph)
    }
}