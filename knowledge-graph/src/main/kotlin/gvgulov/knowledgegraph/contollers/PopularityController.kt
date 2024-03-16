package gvgulov.knowledgegraph.contollers

import gvgulov.knowledgegraph.dto.popularity.PopularityDto
import gvgulov.knowledgegraph.service.PopularityEntityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/popularity")
class PopularityController(
    private val service: PopularityEntityService
) {
    @GetMapping("nodes")
    fun getPopularityNodes(): List<PopularityDto> = service.getPopularityNodes().map { it.toDto() }

    @GetMapping("edges")
    fun getPopularityEdges(): List<PopularityDto> = service.getPopularityEdges().map { it.toDto() }

    @GetMapping("properties")
    fun getPopularityProperties(): List<PopularityDto> = service.getPopularityProperties().map { it.toDto() }

}