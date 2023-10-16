package by.javaguru.mapper;

import by.javaguru.dto.UserBoardGameDto;
import by.javaguru.entity.UserBoardGame;

public class UserBoardGameMapper implements Mapper<UserBoardGame, UserBoardGameDto> {
    private static final UserBoardGameMapper INSTANCE = new UserBoardGameMapper();

    private UserBoardGameMapper() {

    }

    public static UserBoardGameMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public UserBoardGameDto mapFrom(UserBoardGame userBoardGame) {
        return UserBoardGameDto.builder()
                .boardGame(userBoardGame.getBoardGame())
                .purchaseDate(userBoardGame.getPurchaseDate())
                .price(userBoardGame.getPrice())
                .build();
    }
}
