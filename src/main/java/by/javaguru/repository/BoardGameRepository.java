package by.javaguru.repository;

import by.javaguru.entity.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long>  {
}
