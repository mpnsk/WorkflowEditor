package com.paunoski.workfloweditor.editarea.events;

import com.paunoski.workfloweditor.editarea.elements.Place;
import com.paunoski.workfloweditor.editarea.elements.Transition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

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

        ArrayList<Place> places = new ArrayList<>();
        places.add(place);
        ArrayList<Transition> transitions = new ArrayList<>();
        transitions.add(transition);
        new ModeInitial(root, places, transitions);

        root.getChildren().addAll(draggablePlace, draggableTransition);
    }

}
