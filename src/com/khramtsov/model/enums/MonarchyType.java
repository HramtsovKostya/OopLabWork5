package com.khramtsov.model.enums;

public enum MonarchyType {
    ABSOLUTE("Абсолютная"),
    CONSTITUTIONAL("Конституционная"),
    DUALISTIC("Дуалистическая");

    private final String name;
    MonarchyType(String name) { this.name = name; }

    public static MonarchyType convert(int number) {
        for (MonarchyType value : values()) {
            if (value.ordinal() == number - 1) return value;
        } return null;
    }

    @Override public String toString() { return name; }
}