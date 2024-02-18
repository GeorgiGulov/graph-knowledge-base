package gvgulov.knowledgegraph.config


import gvgulov.knowledgegraph.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfiguration {
    @Autowired
    private lateinit var service: UserService

    @Autowired
    private lateinit var successHandler: OAuth2LoginSuccessHandler

    @Value("\${frontend.url}")
    private lateinit var frontendUrl: String

    @Bean
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .cors { corsConfig ->
                corsConfig.configurationSource(corsConfigurationSource())
            }
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/auth/login").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { loginConfigurer ->
                loginConfigurer
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/process_login")
                    .defaultSuccessUrl("/auth/successfulLogin", true)
                    .failureUrl("/auth/login?error")
            }
//            .oauth2Login { oath2 ->
//                oath2.successHandler(successHandler)
//            }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val config = CorsConfiguration().apply {
            allowedOrigins = listOf(frontendUrl)
            allowedHeaders= listOf("*")
            allowedMethods= listOf("*")
            allowCredentials = true
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", config)
        }
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

}