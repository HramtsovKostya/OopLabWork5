package com.khramtsov.model.states;

import com.khramtsov.model.enums.HeadOfState;
import com.khramtsov.model.enums.MonarchyType;
import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "Королевство")

public class Kingdom extends Monarchy {
    public Kingdom() { }

    public Kingdom(@NotNull String name,
        @NotNull String capital,
        int area, int population,
        @NotNull MonarchyType type) {

        super(name, capital, area, population, type);
    }

    @Override public HeadOfState getHead() {
        return HeadOfState.KING;
    }

    @Override public String toString() {
        return super.toString().replace(
            "Монархия", "Королевство");
    }
}