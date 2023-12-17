package by.javaguru.service;

import by.javaguru.entity.UserBoardGame;
import by.javaguru.repository.UserBoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBoardGameService {

    private final UserBoardGameRepository userBoardGameRepository;

    public List<UserBoardGame> findAllByUserId(Long userId) {
        return userBoardGameRepository.findAllByUserId(userId);
    }

    public void deleteById(Long id) {
        userBoardGameRepository.deleteById(id);
    }
}
