package com.paunoski.workfloweditor;

import javafx.scene.shape.Rectangle;

import java.util.Set;

public class Transition extends Rectangle implements PlaceOrTransition , MyShape{
    int tokens;
    Set<Place> places;

    public Transition() {
        setHeight(40);
        setWidth(60);
    }
}
