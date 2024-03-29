package gvgulov.knowledgegraph.dao

import gvgulov.knowledgegraph.component.GraphConnection
import gvgulov.knowledgegraph.graphEntity.GraphData
import gvgulov.knowledgegraph.traversal.addEdge
import gvgulov.knowledgegraph.traversal.addNode
import gvgulov.knowledgegraph.utils.getLogger
import gvgulov.knowledgegraph.traversal.query
import gvgulov.knowledgegraph.traversal.toGraphData
import org.springframework.stereotype.Service

@Service
class GraphDao(
    val connection: GraphConnection
) {
    private val logger = getLogger()

    fun saveGraph(newGraph: GraphData) {
        logger.info("Saving graph [nodes: ${newGraph.nodes.size}; edges: ${newGraph.edges.size}]")
        val traversal = connection.traversal

        val mapNode = newGraph.nodes.associateWith { node ->
            traversal.addNode(newNode = node)
        }

        newGraph.edges.forEach { newEdge ->
            val newSourceId = mapNode[newEdge.source]
            val newTargetId = mapNode[newEdge.target]

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

    fun getAllData(): GraphData =
        (connection.traversal.V().toList() + connection.traversal.E().toList()).toGraphData()

}