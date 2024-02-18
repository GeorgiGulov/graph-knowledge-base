package gvgulov.knowledgegraph.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2LoginSuccessHandler : SavedRequestAwareAuthenticationSuccessHandler() {

    @Value("\${frontend.url}")
    private lateinit var frontendUrl: String

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        this.isAlwaysUseDefaultTargetUrl = true
        this.defaultTargetUrl = frontendUrl

        super.onAuthenticationSuccess(request, response, authentication)
    }
}