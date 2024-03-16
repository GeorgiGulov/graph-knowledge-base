package gvgulov.knowledgegraph.utils

import gvgulov.knowledgegraph.database.entity.UserEntity
import gvgulov.knowledgegraph.graphEntity.EdgeData
import gvgulov.knowledgegraph.graphEntity.GraphData
import gvgulov.knowledgegraph.graphEntity.NodeData
import gvgulov.knowledgegraph.graphEntity.PropertyData
import gvgulov.knowledgegraph.loader.DataLoader
import gvgulov.knowledgegraph.service.UserService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class InitiateTestData(
    val loader: DataLoader,
    val userService: UserService
) {

    @PostConstruct
    fun init() {
        createTestGraveAndSave()
        createTestUser()
    }

    private fun createTestUser() {
        val user = UserEntity(
            username = "test",
            password = "test"
        )
        userService.createNewUser(user)
    }

    private fun createTestGraveAndSave() {
        val graph = testDataGraph()
        loader.saveGraph(graph)
    }

    private fun testDataGraph(): GraphData {
        val student1 = NodeData(
            label = "Студент",
            properties = listOf(
                PropertyData("ФИО", "Иванов Иван Иваныч"),
                PropertyData("Возраст", "20")
            )
        )

        val student2 = NodeData(
            label = "Студент",
            properties = listOf(
                PropertyData("ФИО", "Петров Петр Петрович"),
                PropertyData("Возраст", "21")
            )
        )

        val subjectStudy = NodeData(
            label = "Предмет",
            properties = listOf(
                PropertyData("Название", "Философия"),
            )
        )

        val teacher = NodeData(
            label = "Преподаватель",
            properties = listOf(
                PropertyData("ФИО", "Петров Петр Петрович"),
                PropertyData("Возраст", "40"),
                PropertyData("Стаж работы", "20")
            )
        )

        val graduateStudent = NodeData(
            label = "Магистрант",
            properties = listOf(
                PropertyData("ФИО", "Сергеев Сергей Сергеевич"),
                PropertyData("Возраст", "21")
            )
        )

        val studying1 = EdgeData(
            label = "обучается у",
            source = student1,
            target = teacher,
        )

        val studying2 = EdgeData(
            label = "обучается у",
            source = graduateStudent,
            target = teacher,
        )

        val studies = EdgeData(
            label = "изучает",
            source = student1,
            target = subjectStudy,
        )

        val teaches = EdgeData(
            label = "преподает",
            source = teacher,
            target = subjectStudy,
        )

        return GraphData(
            nodes = listOf(student1, student2, teacher, graduateStudent, subjectStudy),
            edges = listOf(studying1, studying2, studies, teaches)
        )
    }

}