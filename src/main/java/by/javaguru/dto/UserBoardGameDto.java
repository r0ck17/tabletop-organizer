package by.javaguru.dto;

import by.javaguru.entity.BoardGame;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserBoardGameDto {

    String id;
    BoardGame boardGame;
    String price;
    String purchaseDate;
}
