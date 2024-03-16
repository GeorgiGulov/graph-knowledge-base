package gvgulov.knowledgegraph.traversal

import gvgulov.knowledgegraph.graphEntity.EdgeData
import gvgulov.knowledgegraph.graphEntity.GraphData
import gvgulov.knowledgegraph.graphEntity.NodeData
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Edge
import org.apache.tinkerpop.gremlin.structure.Element
import org.apache.tinkerpop.gremlin.structure.Vertex


//fun GraphTraversalSource.query(
//    queryGraph: GraphData,
//): GraphData {
//    val nodes = queryGraph.nodes
//    val edges = queryGraph.edges
//
//    val listUsedNodeId = edges.flatMap { edge ->
//        listOf(edge.source.id, edge.target.id)
//    }.distinct()
//
//    val unusedNode = nodes.filter { it.id !in listUsedNodeId }
//    val listElement = mutableListOf<Element>()
//
//    var traversal = this.V()
//    var needV = false
//
//    edges.forEachIndexed { index, searchEdge ->
//        if (index > 0) traversal = traversal.V()
//        traversal = traversal.edge(searchEdge)
//        needV = true
//    }
//
//    unusedNode.forEachIndexed { index, searchNode ->
//        if (index > 0 || needV) {
//            traversal = traversal.V()
//            needV = true
//        }
//        traversal = traversal.node(searchNode).`as`(searchNode.id)
//    }
//
//    val elements = traversal.path().unfold<Element>().dedup().toSet()
//    listElement.addAll(elements)
//
//
//    return listElement.toGraphData()
//}

data class NodeToUse(
    val node: NodeData,
    var usedNode: Boolean
)

data class NodeToUnusedEdges(
    val node: NodeData,
    var unUsedNode: Boolean,
    val connectedEdges: MutableList<EdgeData>
)

fun GraphTraversalSource.query(
    queryGraph: GraphData,
): GraphData {
    val nodes = queryGraph.nodes
    val edges = queryGraph.edges

    val nodesToEdges = nodes.map { node ->
        NodeToUnusedEdges(
            node = node,
            unUsedNode = true,
            connectedEdges = edges.filter { it.source.id == node.id }.toMutableList()
        )
    }

    var currentNode = nodesToEdges.firstOrNull()
    val traversal = this.V()
    var neededV = false

    val placeReturn = mutableListOf<String>()

    while (currentNode != null) {

        if (currentNode.unUsedNode) {
            if(neededV) {
                traversal.V()
            }

            neededV = true
            traversal.node(currentNode.node)
            currentNode.unUsedNode = false
        }

        if (currentNode.connectedEdges.isNotEmpty()) {
            val edge = currentNode.connectedEdges.removeFirst()

            traversal.`as`(currentNode.node.id)
            placeReturn.add(currentNode.node.id)

            traversal.edge(edge, currentNode.node)

            val nodeNexId = if (currentNode.node.id == edge.source.id) {
                edge.target.id
            } else {
                edge.source.id
            }

            currentNode = nodesToEdges.single { it.node.id == nodeNexId }
            currentNode.unUsedNode = false
        } else {
            if(placeReturn.isNotEmpty()) {
                val returnId = placeReturn.removeLast()
                traversal.select<Vertex>(returnId)
                currentNode = nodesToEdges.single { it.node.id == returnId }
            } else {
                currentNode = nodesToEdges.firstOrNull { it.unUsedNode }
            }
        }
    }

    val result = traversal.path().unfold<Element>().dedup().toList().toGraphData()
    return result
}


fun List<Element>.toGraphData(): GraphData {
    val nodes = this.filterIsInstance<Vertex>().map { it.toNodeData() }.distinct()
    val edges = this.filterIsInstance<Edge>().map { it.toEdgeData() }.distinct()
    return GraphData(nodes = nodes, edges = edges)
}