package quizapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapi.exception.ResourceNotFoundException;
import quizapi.model.User;
import quizapi.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return user;
    }

    public User findOrCreateByUsername(String username) {
        User user;
        if (userRepository.existsByUsername(username)) {
            user = userRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        } else {
            user = userRepository.save(new User(username));
        }
        return user;
    }

    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }
}
