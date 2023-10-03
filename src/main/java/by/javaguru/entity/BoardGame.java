package by.javaguru.entity;

import java.util.Objects;

public class BoardGame {
    private Long id;
    private String name;
    private Integer price;
    private Short year;
    private String language;
    private String publisher;
    private short minPlayers;
    private short maxPlayers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public short getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(short minPlayers) {
        this.minPlayers = minPlayers;
    }

    public short getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(short maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame boardGame = (BoardGame) o;
        return minPlayers == boardGame.minPlayers && maxPlayers == boardGame.maxPlayers && Objects.equals(id, boardGame.id) && Objects.equals(name, boardGame.name) && Objects.equals(price, boardGame.price) && Objects.equals(year, boardGame.year) && Objects.equals(language, boardGame.language) && Objects.equals(publisher, boardGame.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, year, language, publisher, minPlayers, maxPlayers);
    }

    @Override
    public String toString() {
        return "BoardGame{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", year=" + year +
               ", language='" + language + '\'' +
               ", publisher='" + publisher + '\'' +
               ", minPlayers=" + minPlayers +
               ", maxPlayers=" + maxPlayers +
               '}';
    }
}
