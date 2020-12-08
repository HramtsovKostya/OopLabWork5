package com.khramtsov.model.enums;

public enum HeadOfState {
    MONARCH("Монарх"),
    PRESIDENT("Президент"),
    KING("Король");

    private final String name;
    HeadOfState(String name) { this.name = name; }

    @Override public String toString() { return name; }
}