package gvgulov.knowledgegraph.dao

import gvgulov.knowledgegraph.component.GraphConnection
import gvgulov.knowledgegraph.entity.GraphData
import gvgulov.knowledgegraph.traversal.addEdge
import gvgulov.knowledgegraph.traversal.addNode
import gvgulov.knowledgegraph.utils.getLogger
import knowledgegraph.server.database.graph.query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GraphDao {

    @Autowired
    lateinit var connection: GraphConnection
    private val logger = getLogger()

    fun saveGraph(newGraph: GraphData) {
        val traversal = connection.traversal

        val mapNode = newGraph.nodes.associateWith { node ->
            traversal.addNode(newNode = node)
        }

        newGraph.edges.forEach { newEdge ->
            val newSourceId = mapNode[newEdge.source]
            val newTargetId = mapNode[newEdge.source]

            if (newSourceId != null && newTargetId != null) {
                val edge = newEdge.copy(
                    source = newEdge.source.copy(id = newSourceId),
                    target = newEdge.target.copy(id = newTargetId)
                )

                traversal.addEdge(edge)
            } else {
                logger.warn("Failed to save edge due to problems with vertices")
            }
        }
    }

    fun executeQuery(queryGraph: GraphData): GraphData =
        connection.traversal.query(queryGraph = queryGraph)

}