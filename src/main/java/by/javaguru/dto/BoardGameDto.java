package by.javaguru.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardGameDto {

    private String name;
    private String year;
    private String language;
    private String publisher;
    private String minPlayers;
    private String maxPlayers;
}
