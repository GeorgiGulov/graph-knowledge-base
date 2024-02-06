package gvgulov.knowledgegraph.contollers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthController {

    @GetMapping("/login")
    fun loginPage():String = "login"

}