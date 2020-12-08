package com.khramtsov.model.states;

import com.khramtsov.model.enums.HeadOfState;
import com.khramtsov.model.enums.MonarchyType;
import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "Монархия")

public class Monarchy extends State {

    @XmlElement(name = "type")
    protected MonarchyType type;

    public Monarchy() { }

    public Monarchy(@NotNull String name,
        @NotNull String capital,
        int area, int population,
        @NotNull MonarchyType type) {

        super(name, capital, area, population);

        if (type == null) {
            String error = "Тип монархии не может быть равным null!";
            throw new IllegalArgumentException(error);
        } this.type = type;
    }

    public MonarchyType getType() { return type; }

    @Override public HeadOfState getHead() {
        return HeadOfState.MONARCH;
    }

    @Override public boolean hasParliament() {
        return type != MonarchyType.ABSOLUTE;
    }

    @Override public String toString() {
        return "\nМонархия: Название = '" + name + "'," +
            "\n\tСтолица = '" + capital + "'," +
            "\n\tТип монархии = " + type + "," +
            "\n\tПлощадь = " + area + " кв. км," +
            "\n\tНаселение = " + population + " чел.\n";
    }
}