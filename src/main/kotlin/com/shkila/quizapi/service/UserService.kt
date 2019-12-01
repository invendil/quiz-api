package com.shkila.quizapi.service

import com.shkila.quizapi.exception.ResourceNotFoundException
import com.shkila.quizapi.model.User
import com.shkila.quizapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun findOrCreateByUsername(username: String): User {
        return if (userRepository.existsByUsername(username) == true) {
            userRepository
                    .findByUsername(username)
                    .orElseThrow { ResourceNotFoundException("User", "username", username) }
        } else {
            userRepository.save(User(username = username))
        }
    }

    fun findByUsername(username: String): User {
        return userRepository
                .findByUsername(username)
                .orElseThrow { ResourceNotFoundException("User", "username", username) }
    }
}
