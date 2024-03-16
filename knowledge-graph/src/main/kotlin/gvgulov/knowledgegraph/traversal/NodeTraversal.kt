package gvgulov.knowledgegraph.traversal

import gvgulov.knowledgegraph.graphEntity.NodeData
import gvgulov.knowledgegraph.graphEntity.PropertyData
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Vertex

fun GraphTraversalSource.addNode(
    newNode: NodeData
): String = this.addV(newNode.label)
    .property(newNode.properties.associate { it.label to it.value })
    .next()
    .id().toString()


fun GraphTraversal<Vertex, Vertex>.node(
    searchNode: NodeData
): GraphTraversal<Vertex, Vertex> =
    this
        .hasLabel(searchNode.label)
        .properties(searchNode.properties)


fun GraphTraversal<Vertex, Vertex>.properties(
    properties: List<PropertyData>
): GraphTraversal<Vertex, Vertex> = this.apply {
    properties.forEach {
        this.has(it.label, it.value)
    }
}


fun Vertex.toNodeData(): NodeData = NodeData(
    id = this.id().toString(),
    label = this.label().toString(),
    properties = getProperty()
)


fun Vertex.getProperty(): List<PropertyData> =
    this.properties<Any>().asSequence().map { property ->
        PropertyData(
            id = property.id().toString(),
            label = property.label().toString(),
            value = property.value()
        )
    }.toList()