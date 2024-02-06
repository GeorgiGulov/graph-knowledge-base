package gvgulov.knowledgegraph.contollers

import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView


@Controller
@RequestMapping("/auth")
class AuthController {

    @GetMapping("/login")
    fun loginPage(): String = "login"

    @GetMapping("/successfulLogin")
    fun successfulLogin(response: HttpServletResponse) = ModelAndView("redirect:http://localhost:3000");

}