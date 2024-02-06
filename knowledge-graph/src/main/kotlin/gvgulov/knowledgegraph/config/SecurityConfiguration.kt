package gvgulov.knowledgegraph.config


import gvgulov.knowledgegraph.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.SecurityFilterChain


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
                .requestMatchers("/auth/login", "error").permitAll()
                .anyRequest()
                .authenticated()
        }.formLogin { loginConfigurer ->
            loginConfigurer
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/index")
                .failureUrl("/auth/login?error")

        }.csrf { it.disable() }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

//    @Bean
//    fun daoAuthenticationProvider(): DaoAuthenticationProvider =
//        DaoAuthenticationProvider().apply {
//            setPasswordEncoder(passwordEncoder())
//            setUserDetailsService(service)
//        }
//
//
//    @Bean
//    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
//        configuration.authenticationManager
}