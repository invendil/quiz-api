package quizapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapi.exception.ResourceNotFoundException;
import quizapi.modal.User;
import quizapi.repository.UserRepository;

@Service
public class UserService implements ServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return user;
    }

    @Override
    public void removeById(Long id) {
        return;
    }
}
