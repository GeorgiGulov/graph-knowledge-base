package gvgulov.knowledgegraph.service

import gvgulov.knowledgegraph.database.model.PopularityEntity
import gvgulov.knowledgegraph.repository.PopularityEntityRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PopularityEntityService(
    val repository: PopularityEntityRepository
) {
    @Transactional
    fun incrementPopularity(label: String) {
        val currentPopularity = findByLabel(label)

        if (currentPopularity != null) {
            val newSize = currentPopularity.size + 1
            repository.save(currentPopularity.copy(size = newSize))
        } else {
            val newEntity = PopularityEntity(label = label, size = 1)
            repository.save(newEntity)
        }
    }


    fun findByLabel(label: String): PopularityEntity? =
        repository.findByLabel(label).takeIf { it.isPresent }?.get()

}