package com.khramtsov.model.states;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.khramtsov.model.enums.HeadOfState;
import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    property = "@class")

@XmlSeeAlso(value = {
    Monarchy.class,
    Republic.class,
    Kingdom.class })

@XmlRootElement
public abstract class State
    implements Serializable {

    protected String name;
    protected String capital;
    protected int area;
    protected int population;

    public State() { }

    protected State(@NotNull String name,
        @NotNull String capital,
        int area, int population) {

        setName(name);
        setCapital(capital);
        setArea(area);
        setPopulation(population);
    }

    public String getName() { return name; }
    public String getCapital() { return capital; }
    public int getArea() { return area; }
    public int getPopulation() { return population; }

    public void setName(@NotNull String name) {
        if (name == null || name.isEmpty()) {
            String error = "Название государства не может " +
                "равняться null или пустой строке!";
            throw new IllegalArgumentException(error);
        } this.name = name;
    }

    public void setCapital(@NotNull String capital) {
        if (capital == null || capital.isEmpty()) {
            String error = "Название столицы не может " +
                "равняться null или пустой строке!";
            throw new IllegalArgumentException(error);
        } this.capital = capital;
    }

    public void setArea(int area) {
        if (area <= 0) {
            String error = "Площадь государства должна " +
                "равняться целому положительному числу!";
            throw new IllegalArgumentException(error);
        } this.area = area;
    }

    public void setPopulation(int population) {
        if (population <= 0) {
            String error = "Население государства должно " +
                "равняться целому положительному числу!";
            throw new IllegalArgumentException(error);
        } this.population = population;
    }

    @JsonIgnore public int getDensity() {
        return (int) Math.ceil((double) population / area);
    }

    @JsonIgnore public abstract HeadOfState getHead();
    public abstract boolean hasParliament();
}