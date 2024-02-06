package gvgulov.knowledgegraph.config


import gvgulov.knowledgegraph.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfiguration {
    @Autowired
    lateinit var service: UserService

    @Bean
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain {
        http.authorizeHttpRequests { authz ->
            authz
                .requestMatchers("/auth/login").permitAll()
                .anyRequest().authenticated()
        }.formLogin { loginConfigurer ->
            loginConfigurer
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/auth/successfulLogin", true)
                .failureUrl("/auth/login?error")

        }.csrf { it.disable() }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

}