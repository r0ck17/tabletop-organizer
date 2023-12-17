package by.javaguru.repository;

import by.javaguru.entity.UserBoardGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBoardGameRepository extends JpaRepository <UserBoardGame, Long> {

    List<UserBoardGame> findAllByUserId(Long userId);
}
