package com.paunoski.workfloweditor.editarea;

import com.paunoski.workfloweditor.Place;
import com.paunoski.workfloweditor.Transition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class EditArea {
    @FXML
    AnchorPane root;


    public void initialize() {
        Place place = new Place();
        place.relocate(150, 75);
        Node draggablePlace = place.makeDraggable(place);


        Transition transition = new Transition();
        transition.relocate(250, 75);
        Node draggableTransition = transition.makeDraggable(transition);

        Line line = new Line();
        line.startXProperty().bind(place.layoutXProperty().add(place.translateXProperty().add(place.getRadius())));
        line.startYProperty().bind(place.layoutYProperty().add(place.translateYProperty()));
        line.endXProperty().bind(transition.layoutXProperty().add(transition.translateXProperty()));
        line.endYProperty().bind(transition.layoutYProperty().add(transition.translateYProperty().add(transition.getHeight() / 2)));


        place.setOnMouseClicked(click -> {
            if (click.getButton().equals(MouseButton.PRIMARY)) {
                System.out.println("primary");
                if (click.getClickCount() == 2) {
                    System.out.println("double");

                    Line mouseLine = new Line();
                    mouseLine.startXProperty().bind(place.layoutXProperty().add(place.translateXProperty()));
                    mouseLine.startYProperty().bind(place.layoutYProperty().add(place.translateYProperty()));
                    mouseLine.setEndX(click.getSceneX());
                    mouseLine.setEndY(click.getSceneY());
                    root.getChildren().add(mouseLine);
                    root.setOnMouseMoved(move -> {
                        mouseLine.setEndX(move.getSceneX());
                        mouseLine.setEndY(move.getSceneY());
                    });
                    System.out.println("setting up click listener");
                    transition.setOnMouseClicked(event -> {
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            System.out.println(event.getClickCount());
                            root.setOnMouseMoved(null);
                            mouseLine.endXProperty().bind(transition.layoutXProperty().add(transition.translateXProperty()));
                            mouseLine.endYProperty().bind(transition.layoutYProperty().add(transition.translateYProperty()));
                        }
                    });
                }
            }
        });

        root.getChildren().addAll(draggablePlace, draggableTransition);
    }

}
