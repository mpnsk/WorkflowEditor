package com.paunoski.workfloweditor.editarea;

import com.paunoski.workfloweditor.Place;
import com.paunoski.workfloweditor.PlaceOrTransition;
import com.paunoski.workfloweditor.Transition;

import java.util.Collection;

public class ModeInitial {


    public ModeInitial(Collection<Place> places, Collection<Transition> transitions) {
        Generic<Place, Transition> placeToTransition = new Generic<>(places, transitions);
        Generic<Transition, Place> transitionToPlace = new Generic<>(transitions, places);
    }


    class Generic<X, Y extends PlaceOrTransition> {
        public Generic(Collection<X> source, Collection<Y> target) {

        }
    }
}
