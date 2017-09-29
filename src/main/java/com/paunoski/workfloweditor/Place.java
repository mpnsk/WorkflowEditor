package com.paunoski.workfloweditor;

import javafx.scene.input.MouseButton;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Set;

public class Place extends Circle implements PlaceOrTransition {
    int tokens;
    Set<Transition> transitions;

    public Place() {
        setRadius(25);
    }

    private Line connectToTransition() {

        Line line = new Line();
        line.startXProperty().bind(layoutXProperty().add(translateXProperty()));
        line.startYProperty().bind(layoutYProperty().add(translateYProperty()));
        line.setOnMouseMoved(event -> {
            line.setEndX(event.getSceneX());
            line.setEndY(event.getSceneY());
        });
        return line;
//        line.endXProperty().bind(transition.layoutXProperty().add(transition.translateXProperty()));
//        line.endYProperty().bind(transition.layoutYProperty().add(transition.translateYProperty().add(transition.getHeight() / 2)));
    }

    public Line connectOnCLickListener() {
        final Line[] line = new Line[1];
        setOnMouseClicked(event -> {
            System.out.println(event);
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                System.out.println("primary");
                if (event.getClickCount() == 2) {
                    System.out.println("double");
                    line[0] = connectToTransition();
                }
            }
        });
        return line[0];
    }

}
