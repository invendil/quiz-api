package quizapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapi.exception.ResourceNotFoundException;
import quizapi.model.*;
import quizapi.payload.GameSessionPayload;
import quizapi.repository.CategoryRepository;
import quizapi.repository.GameSessionRepository;
import quizapi.repository.QuestionRepository;
import quizapi.util.DataUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class QuestionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionService.class);

}
