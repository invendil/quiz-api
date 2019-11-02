package quizapi.service;

public interface ServiceInterface {
    <T> T findById(Long id);
    void removeById(Long id);
}
