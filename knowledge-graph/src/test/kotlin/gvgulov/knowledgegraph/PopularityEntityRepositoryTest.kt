package gvgulov.knowledgegraph

import gvgulov.knowledgegraph.database.model.PopularityEntity
import gvgulov.knowledgegraph.repository.PopularityEntityRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PopularityEntityRepositoryTest{
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var repository: PopularityEntityRepository

    @Test
    fun `save operation`() {
        val newEntity = PopularityEntity(
            label = "Человек",
            size = 1
        )
        val saveEntity = repository.save(newEntity)
        val entityInBd: PopularityEntity? = entityManager.find(PopularityEntity::class.java, saveEntity.id)

        assertNotNull(entityInBd)
        assertEquals(entityInBd?.label, newEntity.label)
    }

    @Test
    fun `update operation`() {
        val newEntity = PopularityEntity(
            label = "Человек",
            size = 1
        )

        val entityInBd: PopularityEntity = entityManager.persist(newEntity)
        val updateEntity = entityInBd.copy(size = 10)
        repository.save(updateEntity)

        val entityInBdAfterUpdated: PopularityEntity? = entityManager.find(PopularityEntity::class.java, entityInBd.id)

        assertNotNull(entityInBdAfterUpdated)
        assertEquals(entityInBdAfterUpdated?.size, updateEntity.size)
    }


    @Test
    fun `find by id operation`() {
        val newEntity = PopularityEntity(
            label = "Человек",
            size = 1
        )
        val saveEntity: PopularityEntity = entityManager.persist(newEntity)
        checkNotNull(saveEntity.id)
        val entityInBd: PopularityEntity? = repository.findById(saveEntity.id!!).takeIf { it.isPresent }?.get()

        assertNotNull(entityInBd)
        assertEquals(entityInBd?.label, newEntity.label)
    }

    @Test
    fun `delete operation`() {
        val newEntity = PopularityEntity(
            label = "Человек",
            size = 1
        )
        val saveEntity: PopularityEntity = entityManager.persist(newEntity)
        repository.delete(newEntity)

        val entityInBdAfterDelete: PopularityEntity? = entityManager.find(PopularityEntity::class.java, saveEntity.id)
        assertNull(entityInBdAfterDelete)
    }

}