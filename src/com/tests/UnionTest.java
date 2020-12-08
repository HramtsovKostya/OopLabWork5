package com.tests;

import com.khramtsov.model.Union;
import com.khramtsov.model.enums.MonarchyType;
import com.khramtsov.model.enums.RepublicType;
import com.khramtsov.model.states.*;

import org.junit.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionTest {
    private static Union union;
    private static List<State> states;

    private static State monarchy;
    private static State republic;
    private static State kingdom;

    @BeforeClass public static void beforeClass() {
        monarchy = new Monarchy(
            "Россия", "Москва",
            123, 34534,
            MonarchyType.ABSOLUTE);

        republic = new Republic(
            "Китай", "Пекин",
            343, 643532,
            RepublicType.PRESIDENTIAL);

        kingdom = new Monarchy(
            "Англия", "Лондон",
            4353, 43532,
            MonarchyType.CONSTITUTIONAL);
    }

    @AfterClass public static void AfterClass() {
        union.clear();
        Assert.assertFalse(union.isNotEmpty());
    }

    @Before public void setUp() {
        states = new ArrayList<>();
        union = new Union();

        states.addAll(Arrays.asList(
            monarchy, republic, kingdom));

        union.add(monarchy);
        union.add(republic);
        union.add(kingdom);
    }

    @After public void tearDown() {
        Assert.assertEquals(states, union.getStates());
    }

    @Test public void add() {
        State state = new Republic(
            "США", "Вашингтон",
            323, 3423,
            RepublicType.PARLIAMENTARY);

        states.add(state); union.add(state);

        State expectedState = states.get(states.size() - 1);
        State actualState = union.get(union.size() - 1);

        Assert.assertEquals(expectedState, actualState);
    }

    @Test public void remove() {
        states.remove(monarchy);
        union.remove(monarchy);

        Assert.assertEquals(states.size(), union.size());
        Assert.assertNull(union.find(monarchy.getName()));
    }

    @Test public void find() {
        State actualState1 = union.find(republic.getName());
        State actualState2 = union.find("Венгрия");

        Assert.assertEquals(republic, actualState1);
        Assert.assertNull(actualState2);
    }
}