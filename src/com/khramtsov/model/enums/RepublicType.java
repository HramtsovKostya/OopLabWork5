package com.khramtsov.model.enums;

public enum RepublicType {
    PRESIDENTIAL("Президентская"),
    PARLIAMENTARY("Парламентская"),
    MIXED("Смешанная");

    private final String name;
    RepublicType(String name) { this.name = name; }

    public static RepublicType convert(int number) {
        for (RepublicType value : values()) {
            if (value.ordinal() == number - 1) return value;
        } return null;
    }

    @Override public String toString() { return name; }
}