package com.khramtsov.model.states;

import com.khramtsov.model.enums.RepublicType;
import com.khramtsov.model.enums.HeadOfState;
import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "Республика")

public class Republic extends State {

    @XmlElement(name = "type")
    protected RepublicType type;

    public Republic() { }

    public Republic(@NotNull String name,
        @NotNull String capital,
        int area, int population,
        @NotNull RepublicType type) {

        super(name, capital, area, population);

        if (type == null) {
            String error = "Тип республики не может быть равным null!";
            throw new IllegalArgumentException(error);
        } this.type = type;
    }

    public RepublicType getType() { return type; }

    @Override public HeadOfState getHead() {
        return HeadOfState.PRESIDENT;
    }

    @Override public boolean hasParliament() {
        return type != RepublicType.PRESIDENTIAL;
    }

    @Override public String toString() {
        return "\nРеспублика: Название = '" + name + "'," +
            "\n\tСтолица = '" + capital + "'," +
            "\n\tТип республики = " + type + "," +
            "\n\tПлощадь = " + area + " кв. км," +
            "\n\tНаселение = " + population + " чел.\n";
    }
}