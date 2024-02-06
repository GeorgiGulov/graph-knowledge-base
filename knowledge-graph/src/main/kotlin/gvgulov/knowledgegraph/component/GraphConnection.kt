package gvgulov.knowledgegraph.component

import gvgulov.knowledgegraph.utils.getLogger
import jakarta.annotation.PostConstruct
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class GraphConnection(
    @Value("\${graphDatabaseName}")
    val name: String
) {
    val graph: Graph = TinkerGraph.open()
    val traversal: GraphTraversalSource = AnonymousTraversalSource.traversal().withEmbedded(graph)

    @PostConstruct
    fun init() {
        getLogger().info("Connection to graph database [${name}]")
    }

}