package by.javaguru.entity;

import java.util.Objects;

public class UserBoardGame {
    private Long userId;
    private Long boardGameId;

    public UserBoardGame() {

    }

    public UserBoardGame(Long userId, Long boardGameId) {
        this.userId = userId;
        this.boardGameId = boardGameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBoardGameId() {
        return boardGameId;
    }

    public void setBoardGameId(Long boardGameId) {
        this.boardGameId = boardGameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBoardGame that = (UserBoardGame) o;
        return Objects.equals(userId, that.userId) && Objects.equals(boardGameId, that.boardGameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, boardGameId);
    }

    @Override
    public String toString() {
        return "UserBoardGame{" +
               "userId=" + userId +
               ", boardGameId=" + boardGameId +
               '}';
    }
}
