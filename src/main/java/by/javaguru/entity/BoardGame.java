package by.javaguru.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardGame {
    private Long id;
    private String name;
    private Integer price;
    private Short year;
    private String language;
    private String publisher;
    private short minPlayers;
    private short maxPlayers;
}
