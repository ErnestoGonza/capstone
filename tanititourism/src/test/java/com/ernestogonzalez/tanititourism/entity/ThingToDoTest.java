package com.ernestogonzalez.tanititourism.entity;

import org.junit.jupiter.api.Test;

import com.ernestogonzalez.tanititourism.entity.DoType;
import com.ernestogonzalez.tanititourism.entity.ThingToDo;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThingToDoTest {

    @Test
    public void addDoTypeTest() {
        ThingToDo thingToDo = new ThingToDo();
        DoType doType = new DoType();

        thingToDo.add(doType);
        assertTrue(thingToDo.getDoTypes().contains(doType), "The Do Type was not added to the ThingToDo.");

    }
}
