package com.paunoski.workfloweditor.editarea;

import com.paunoski.workfloweditor.MyShape;
import com.paunoski.workfloweditor.Place;
import com.paunoski.workfloweditor.Transition;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.Collection;

public class ModeInitial {


    public ModeInitial(Pane root, Collection<Place> places, Collection<Transition> transitions) {
        new Generic<>(root, places, transitions);
        new Generic<>(root, transitions, places);
    }

    class Generic<S extends Node & MyShape, T extends Node & MyShape> {
        Generic(Pane pane, Collection<S> sources, Collection<T> targets) {
            EventType<MouseEvent> mouseClicked = MouseEvent.MOUSE_CLICKED;

            for (S source : sources) {
                EventHandler<MouseEvent> startDragging = (MouseEvent click) -> {
                    if (click.getButton().equals(MouseButton.PRIMARY)
//                    && click.getClickCount() == 2)
                            ) {

                        Line traceLine = new Line();
                        traceLine.startXProperty().bind(source.layoutXProperty().add(source.translateXProperty()));
                        traceLine.startYProperty().bind(source.layoutYProperty().add(source.translateYProperty()));
                        pane.getChildren().add(traceLine);

                        EventHandler<MouseEvent> traceMouse = move -> {
                            traceLine.setEndX(move.getSceneX());
                            traceLine.setEndY(move.getSceneY());
                        };
                        traceMouseOn(pane, traceMouse);

                        setupMouseOver(pane, targets, traceLine, traceMouse);
                    }

                };
                source.addEventHandler(mouseClicked, startDragging);
            }
        }

        private void setupMouseOver(Pane pane, Collection<T> targets, Line mouseLine, EventHandler<MouseEvent> traceMouse) {
            for (T target : targets) {
                target.setOnMouseEntered(event -> {
                    traceMouseOff(pane, traceMouse);
                    mouseLine.endXProperty().bind(target.layoutXProperty().add(target.translateXProperty()));
                    mouseLine.endYProperty().bind(target.layoutYProperty().add(target.translateYProperty()).add(target.heightProperty().divide(2)));
                });
                target.setOnMouseExited(event -> {
                    mouseLine.endXProperty().unbind();
                    mouseLine.endYProperty().unbind();
                    traceMouseOn(pane, traceMouse);
                });
            }
        }

        private void traceMouseOff(Node node, EventHandler<MouseEvent> traceMouse) {
            node.removeEventHandler(MouseEvent.MOUSE_MOVED, traceMouse);
        }

        private void traceMouseOn(Node node, EventHandler<MouseEvent> traceMouse) {
            node.addEventHandler(MouseEvent.MOUSE_MOVED, traceMouse);
        }
    }
}
