package com.khramtsov.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khramtsov.model.states.*;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Union implements Serializable {

    @XmlElementWrapper(name = "states", nillable = true)
    private final List<State> states = new ArrayList<>();

    public Union() { }

    public int size() { return states.size(); }
    public State get(int index) { return states.get(index); }
    public List<State> getStates() { return states;  }

    @JsonIgnore public boolean isNotEmpty() {
        return !states.isEmpty();
    }

    public void add(State state) { states.add(state); }
    public void remove(State state) { states.remove(state); }

    public State find(String name) {
        for (State state : states) {
            if (state != null && state.getName().equalsIgnoreCase(name))
                return state;
        } return null;
    }

    public boolean contains(State state) {
        return find(state.getName()) != null;
    }

    public void clear() {
        while (states.size() > 0) remove(get(0));
    }

    @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        for (State state : states) builder.append(state);
        return builder.toString();
    }
}