package gvgulov.knowledgegraph.contollers

import gvgulov.knowledgegraph.dao.GraphDao
import gvgulov.knowledgegraph.dto.GraphDataDto
import gvgulov.knowledgegraph.loader.DataLoader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GraphController(
    val graphDao: GraphDao,
    val loader: DataLoader,
) {

    @PostMapping("save")
    fun save(
        @RequestBody graph: GraphDataDto,
    ) {
        val newGraph = graph.toGraphData()
        loader.saveGraph(graph = newGraph)
    }

    @PostMapping("search")
    fun search(
        @RequestBody query: GraphDataDto,
    ): GraphDataDto {
        val searchGraph = query.toGraphData()
        return graphDao.executeQuery(searchGraph).toDto()
    }

    @GetMapping("allData")
    fun getAllData(): GraphDataDto = graphDao.getAllData().toDto()

    @GetMapping("test")
    fun test(): String {
        return "123"
    }

}