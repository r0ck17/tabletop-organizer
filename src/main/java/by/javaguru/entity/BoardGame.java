package by.javaguru.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boardgame")
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Short year;

    @Column(name = "language")
    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "min_players")
    private Short minPlayers;

    @Column(name = "max_players")
    private Short maxPlayers;
}
