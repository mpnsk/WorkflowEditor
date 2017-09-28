package com.paunoski.workfloweditor.editarea;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EditArea {
    @FXML
    AnchorPane root;

    private boolean draggable = true;


    public void initialize() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.RED);
        rectangle.setHeight(30);
        rectangle.setWidth(30);

        Node node =  makeDraggable(rectangle);


        Rectangle rectangle2 = new Rectangle();
        rectangle2.setFill(Color.BLUE);
        rectangle2.setHeight(35);
        rectangle2.setWidth(25);
        rectangle2.relocate(50, 0);

        Node node2 =  makeDraggable(rectangle2);
        root.getChildren().addAll(node, node2);
    }

    private Node makeDraggable(final Node node) {
        final DragContext dragContext = new DragContext();
        final Group wrapGroup = new Group(node);

        wrapGroup.addEventFilter(
                MouseEvent.ANY,
                mouseEvent -> {
                    if (draggable) {
                        // disable mouse events for all children
                        mouseEvent.consume();
                    }
                });

        wrapGroup.addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                mouseEvent -> {
                    if (draggable) {
                        // remember initial mouse cursor coordinates
                        // and node position
                        dragContext.mouseAnchorX = mouseEvent.getX();
                        dragContext.mouseAnchorY = mouseEvent.getY();
                        dragContext.initialTranslateX =
                                node.getTranslateX();
                        dragContext.initialTranslateY =
                                node.getTranslateY();
                    }
                });

        wrapGroup.addEventFilter(
                MouseEvent.MOUSE_DRAGGED,
                mouseEvent -> {
                    if (draggable) {
                        System.out.println(node);
                        // shift node from its initial position by delta
                        // calculated from mouse cursor movement
                        node.setTranslateX(
                                dragContext.initialTranslateX
                                        + mouseEvent.getX()
                                        - dragContext.mouseAnchorX);
                        node.setTranslateY(
                                dragContext.initialTranslateY
                                        + mouseEvent.getY()
                                        - dragContext.mouseAnchorY);
                    }
                });

        return wrapGroup;

    }

    private static final class DragContext {
        double mouseAnchorX;
        double mouseAnchorY;
        double initialTranslateX;
        double initialTranslateY;
    }
}
