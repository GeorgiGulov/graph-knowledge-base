package gvgulov.knowledgegraph.contollers

import gvgulov.knowledgegraph.dao.GraphDao
import gvgulov.knowledgegraph.dto.GraphDataDto
import gvgulov.knowledgegraph.dto.Pageable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GraphController {
    @Autowired
    lateinit var graphDao: GraphDao

    @PostMapping("save")
    fun save(
        @RequestBody graph: GraphDataDto,
    ) {
        val newGraph = graph.toGraphData()
        graphDao.saveGraph(newGraph)
    }

    @PostMapping("search")
    fun search(
        @RequestBody query: GraphDataDto,
        @RequestBody pageable: Pageable,
    ): GraphDataDto {
        val searchGraph = query.toGraphData()
        return graphDao.executeQuery(searchGraph).toDto()
    }

    @GetMapping("test")
    fun test(): String {
        return "123"
    }

}