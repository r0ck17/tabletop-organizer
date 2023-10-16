package by.javaguru.dto;

import by.javaguru.entity.BoardGame;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class UserBoardGameDto {
    BoardGame boardGame;
    Integer price;
    LocalDate purchaseDate;
}
