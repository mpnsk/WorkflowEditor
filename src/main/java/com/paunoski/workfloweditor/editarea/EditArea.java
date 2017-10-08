package com.paunoski.workfloweditor.editarea;

import com.paunoski.workfloweditor.Place;
import com.paunoski.workfloweditor.Transition;
import javafx.event.EventHandler;
import javafx.event.EventType;
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


        EventType<MouseEvent> mouseClicked = MouseEvent.MOUSE_CLICKED;
        EventHandler<MouseEvent> startDragging = click -> {
            if (click.getButton().equals(MouseButton.PRIMARY)
//                    && click.getClickCount() == 2)
                    ) {

                Line mouseLine = new Line();
                mouseLine.startXProperty().bind(place.layoutXProperty().add(place.translateXProperty()));
                mouseLine.startYProperty().bind(place.layoutYProperty().add(place.translateYProperty()));
                root.getChildren().add(mouseLine);
                EventHandler<MouseEvent> traceMouse = move -> {
                    mouseLine.setEndX(move.getSceneX());
                    mouseLine.setEndY(move.getSceneY());
                };
                root.setOnMouseMoved(traceMouse);
                transition.setOnMouseEntered(event -> {
                    root.setOnMouseMoved(null);
                    mouseLine.endXProperty().bind(transition.layoutXProperty().add(transition.translateXProperty()));
                    mouseLine.endYProperty().bind(transition.layoutYProperty().add(transition.translateYProperty()).add(transition.heightProperty().divide(2)));
                });
                transition.setOnMouseExited(event -> {
                    mouseLine.endXProperty().unbind();
                    mouseLine.endYProperty().unbind();
                    root.setOnMouseMoved(traceMouse);
                });
            }

        };
        Runnable stopDragging = EventHandlerRegistration.add(place, mouseClicked, startDragging);
        place.addEventHandler(mouseClicked, startDragging);

        root.getChildren().addAll(draggablePlace, draggableTransition);
    }

}
