package com.paunoski.workfloweditor.editarea;

import com.paunoski.workfloweditor.Place;
import com.paunoski.workfloweditor.Transition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

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


        EventHandler<MouseEvent> startDragging = click -> {
            if (click.getButton().equals(MouseButton.PRIMARY)
//                    && click.getClickCount() == 2)
                    ) {

                Line mouseLine = new Line();
                mouseLine.startXProperty().bind(place.layoutXProperty().add(place.translateXProperty()));
                mouseLine.startYProperty().bind(place.layoutYProperty().add(place.translateYProperty()));
                mouseLine.setEndX(click.getSceneX());
                mouseLine.setEndY(click.getSceneY());
                root.getChildren().add(mouseLine);
                EventHandler<MouseEvent> traceMouse = move -> {
                    mouseLine.setEndX(move.getSceneX());
                    mouseLine.setEndY(move.getSceneY());
                };
                root.setOnMouseMoved(traceMouse);
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

        };
        place.addEventHandler(MouseEvent.MOUSE_CLICKED, startDragging);

        root.getChildren().addAll(draggablePlace, draggableTransition);
    }

}
