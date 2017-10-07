package com.paunoski.workfloweditor.editarea;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;

class EventHandlerRegistration {
    static <T extends Event> Runnable add(Node node, EventType<T> type, EventHandler<? super T> handler) {
        node.addEventHandler(type, handler);
        return () -> node.removeEventHandler(type, handler);
    }
}
