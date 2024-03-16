package gvgulov.knowledgegraph.service

import gvgulov.knowledgegraph.database.entity.popularity.EdgePopularityEntity
import gvgulov.knowledgegraph.database.entity.popularity.NodePopularityEntity
import gvgulov.knowledgegraph.database.entity.popularity.PropertyPopularityEntity
import gvgulov.knowledgegraph.graphEntity.GraphData
import gvgulov.knowledgegraph.repository.popularity.EdgePopularityRepository
import gvgulov.knowledgegraph.repository.popularity.NodePopularityRepository
import gvgulov.knowledgegraph.repository.popularity.PropertyPopularityRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PopularityEntityService(
    val nodeRepository: NodePopularityRepository,
    val edgeRepository: EdgePopularityRepository,
    val propertyRepository: PropertyPopularityRepository,
) {
    @Transactional
    fun incrementPopularity(graph: GraphData) {
        val nodes = graph.nodes.groupBy { node ->
            node.label
        }.map {
            it.key to it.value.size
        }.toMap()

        val edges = graph.edges.groupBy { edge ->
            edge.label
        }.map {
            it.key to it.value.size
        }.toMap()

        val properties = graph.nodes
            .flatMap { node ->
                node.properties
            }.groupBy { property ->
                property.label
            }.map {
                it.key to it.value.size
            }.toMap()


        nodes.forEach { labelToSize ->
            val nodePopularity = nodeRepository.findById(labelToSize.key).takeIf { it.isPresent }?.get()

            if (nodePopularity == null) {
                val entity = NodePopularityEntity(
                    label = labelToSize.key,
                    size = labelToSize.value
                )
                nodeRepository.save(entity)
            } else {
                nodePopularity.size += labelToSize.value
            }

        }

        edges.forEach { labelToSize ->
            val edgePopularity = edgeRepository.findById(labelToSize.key).takeIf { it.isPresent }?.get()

            if (edgePopularity == null) {
                val entity = EdgePopularityEntity(
                    label = labelToSize.key,
                    size = labelToSize.value
                )
                edgeRepository.save(entity)
            } else {
                edgePopularity.size += labelToSize.value
            }

        }

        properties.forEach { labelToSize ->
            val propertyPopularity = propertyRepository.findById(labelToSize.key).takeIf { it.isPresent }?.get()

            if (propertyPopularity == null) {
                val entity = PropertyPopularityEntity(
                    label = labelToSize.key,
                    size = labelToSize.value
                )
                propertyRepository.save(entity)
            } else {
                propertyPopularity.size += labelToSize.value
            }

        }

    }


}