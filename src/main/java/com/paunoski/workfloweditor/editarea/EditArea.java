package com.paunoski.workfloweditor.editarea;

import com.paunoski.workfloweditor.Place;
import com.paunoski.workfloweditor.Transition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
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


        root.getChildren().addAll(draggablePlace, draggableTransition);
    }

}
