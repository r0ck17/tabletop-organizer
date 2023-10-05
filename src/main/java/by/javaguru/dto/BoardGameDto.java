package by.javaguru.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardGameDto {
    private String name;
    private Integer price;
    private Short year;
    private String language;
    private String publisher;
    private Short minPlayers;
    private Short maxPlayers;
}
