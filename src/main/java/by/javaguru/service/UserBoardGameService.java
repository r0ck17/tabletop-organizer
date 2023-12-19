package by.javaguru.service;

import by.javaguru.entity.UserBoardGame;
import by.javaguru.repository.UserBoardGameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBoardGameService {

    private final UserBoardGameRepository userBoardGameRepository;

    public List<UserBoardGame> findAllByUserId(Long userId) {
        log.info("Find all games for a user with ID {}", userId);
        return userBoardGameRepository.findAllByUserId(userId);
    }

    public void deleteById(Long id) {
        log.info("Deleting all games for a user with ID {}", id);
        userBoardGameRepository.deleteById(id);
    }
}
