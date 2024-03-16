package gvgulov.knowledgegraph.service

import gvgulov.knowledgegraph.database.entity.UserEntity
import gvgulov.knowledgegraph.repository.user.UserRepository
import gvgulov.knowledgegraph.security.UserDetailsImpl
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: UserRepository
) : UserDetailsService {

    fun createNewUser(user: UserEntity) {
        repository.save(user)
    }

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user = findByUserName(username) ?: throw UsernameNotFoundException("User named [$username] not found")

        return UserDetailsImpl(user = user)
    }

    fun findByUserName(userName: String): UserEntity? =
        repository.findByUsername(userName).takeIf { it.isPresent }?.get()

}