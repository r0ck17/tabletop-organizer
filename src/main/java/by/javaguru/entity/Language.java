package by.javaguru.entity;

import lombok.Getter;

@Getter
public enum Language {
    RU("Русский"), EN("Английский");

    private final String description;

    Language(String description) {
        this.description = description;
    }
}
