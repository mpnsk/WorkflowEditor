package com.paunoski.workfloweditor.editarea.elements;

import javafx.scene.shape.Rectangle;

import java.util.Set;

public class Transition extends Rectangle implements Dragable, MyShape {
    int tokens;
    Set<Place> places;

    public Transition() {
        setHeight(40);
        setWidth(60);
    }
}
