package gvgulov.knowledgegraph.traversal

import gvgulov.knowledgegraph.graphEntity.EdgeData
import gvgulov.knowledgegraph.graphEntity.NodeData
import gvgulov.knowledgegraph.graphEntity.PropertyData
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Edge
import org.apache.tinkerpop.gremlin.structure.Vertex

fun GraphTraversalSource.addEdge(
    newEdge: EdgeData
): String {
    val source = this.V(newEdge.source.id.toLong()).next()
    val target = this.V(newEdge.target.id.toLong()).next()

    return this.addE(newEdge.label)
        .from(source)
        .to(target)
        .property(newEdge.properties.associate { it.label to it.value })
        .next().id().toString()

}


//fun GraphTraversal<Vertex, Vertex>.edge(
//    searchEdge: EdgeData
//): GraphTraversal<Vertex, Vertex> =
//    this
//        .node(searchEdge.source)
//        .`as`(searchEdge.source.id)
//        .select<Vertex>(searchEdge.source.id)
//        .outE()
//        .hasLabel(searchEdge.label)
//        .properties(searchEdge.properties)
//        .inV()
//        .node(searchEdge.target)
//        .`as`(searchEdge.target.id)


fun GraphTraversal<Vertex, Vertex>.edge(
    searchEdge: EdgeData,
    nodeSource: NodeData,
): GraphTraversal<Vertex, Vertex> =
    if (nodeSource.id == searchEdge.source.id) {
        this
            .outE()
            //.hasLabel(searchEdge.label)
            //.properties(searchEdge.properties)
            .inV()
            .node(searchEdge.target)
    } else {
        this
            .inE()
            //.hasLabel(searchEdge.label)
            //.properties(searchEdge.properties)
            .outV()
            .node(searchEdge.source)
    }



fun GraphTraversal<Vertex, Edge>.properties(properties: List<PropertyData>) = this.apply {
    properties.forEach {
        this.has(it.label, it.value)
    }
}

fun Edge.toEdgeData(): EdgeData = EdgeData(
    id = this.id().toString(),
    label = this.label().toString(),
    source = this.outVertex().toNodeData(),
    target = this.inVertex().toNodeData(),
    properties = this.getProperty()
)


fun Edge.getProperty(): List<PropertyData> =
    this.properties<Any>().asSequence().map { property ->
        PropertyData(
            id = "property-edge-${this.id()}",
            label = property.key(),
            value = property.value()
        )
    }.toList()