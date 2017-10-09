package com.paunoski.workfloweditor.editarea.elements;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public interface Dragable {
    boolean draggable = true;

    default Node makeDraggable(Node node) {
        final DragContext dragContext = new DragContext();
        final Group wrapGroup = new Group(node);

//        wrapGroup.addEventFilter(
//                MouseEvent.ANY,
//                mouseEvent -> {
//                    if (draggable) {
//                         disable mouse events for all children
//                        mouseEvent.consume();
//                    }
//                });

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

    final class DragContext {
        double mouseAnchorX;
        double mouseAnchorY;
        double initialTranslateX;
        double initialTranslateY;
    }
}
