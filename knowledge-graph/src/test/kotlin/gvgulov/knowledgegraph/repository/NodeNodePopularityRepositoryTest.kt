package gvgulov.knowledgegraph.repository

import gvgulov.knowledgegraph.database.entity.popularity.NodePopularityEntity
import gvgulov.knowledgegraph.repository.popularity.NodePopularityRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NodeNodePopularityRepositoryTest{
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var repository: NodePopularityRepository

    @Test
    fun `save operation`() {
        val newEntity = NodePopularityEntity(
            label = "Человек",
            size = 1
        )
        repository.save(newEntity)
        val entityInBd: NodePopularityEntity? = entityManager.find(NodePopularityEntity::class.java, newEntity.label)

        assertNotNull(entityInBd)
        assertEquals(entityInBd?.label, newEntity.label)
    }

    @Test
    fun `update operation`() {
        val newEntity = NodePopularityEntity(
            label = "Человек",
            size = 1
        )

        val entityInBd: NodePopularityEntity = entityManager.persist(newEntity)
        entityInBd.size = 10
        repository.save(entityInBd)

        val entityInBdAfterUpdated: NodePopularityEntity? = entityManager.find(NodePopularityEntity::class.java, newEntity.label)

        assertNotNull(entityInBdAfterUpdated)
        assertEquals(entityInBdAfterUpdated?.size, entityInBd.size)
    }


    @Test
    fun `find by id operation`() {
        val newEntity = NodePopularityEntity(
            label = "Человек",
            size = 1
        )
        val saveEntity: NodePopularityEntity = entityManager.persist(newEntity)
        checkNotNull(newEntity.label)
        val entityInBd: NodePopularityEntity? = repository.findById(newEntity.label).takeIf { it.isPresent }?.get()

        assertNotNull(entityInBd)
        assertEquals(entityInBd?.label, newEntity.label)
    }

    @Test
    fun `delete operation`() {
        val newEntity = NodePopularityEntity(
            label = "Человек",
            size = 1
        )
        val saveEntity: NodePopularityEntity = entityManager.persist(newEntity)
        repository.delete(newEntity)

        val entityInBdAfterDelete: NodePopularityEntity? = entityManager.find(NodePopularityEntity::class.java, newEntity.label)
        assertNull(entityInBdAfterDelete)
    }

}