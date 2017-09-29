package com.paunoski.workfloweditor;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

import java.util.Set;

public class Place extends Circle implements PlaceOrTransition {
    int tokens;
    Set<Transition> transitions;

    public Place() {
        setRadius(25);
    }

}
