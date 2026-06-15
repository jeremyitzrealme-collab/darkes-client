package com.darkesclient.core.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Central event system for Darkes Client.
 * Allows modules and systems to subscribe to and fire events.
 */
public class EventBus {
    private static final Logger LOGGER = LoggerFactory.getLogger("EventBus");
    private final Map<Class<?>, List<EventListener<?>>> listeners = new HashMap<>();

    /**
     * Subscribes a listener to events of a specific type.
     * @param eventClass The event class to listen for
     * @param listener The listener callback
     */
    @SuppressWarnings("unchecked")
    public <T extends Event> void subscribe(Class<T> eventClass, EventListener<T> listener) {
        listeners.computeIfAbsent(eventClass, k -> new ArrayList<>())
                 .add(listener);
        LOGGER.debug("Listener registered for event: {}", eventClass.getSimpleName());
    }

    /**
     * Unsubscribes a listener from events.
     * @param eventClass The event class
     * @param listener The listener to remove
     */
    @SuppressWarnings("unchecked")
    public <T extends Event> void unsubscribe(Class<T> eventClass, EventListener<T> listener) {
        List<EventListener<?>> eventListeners = listeners.get(eventClass);
        if (eventListeners != null) {
            eventListeners.remove(listener);
            LOGGER.debug("Listener unregistered for event: {}", eventClass.getSimpleName());
        }
    }

    /**
     * Fires an event to all registered listeners.
     * @param event The event to fire
     */
    @SuppressWarnings("unchecked")
    public void fire(Event event) {
        List<EventListener<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (EventListener<?> listener : eventListeners) {
                try {
                    ((EventListener<Event>) listener).onEvent(event);
                    if (event.isCancelled()) {
                        break;
                    }
                } catch (Exception e) {
                    LOGGER.error("Error in event listener for {}", event.getClass().getSimpleName(), e);
                }
            }
        }
    }

    /**
     * Clears all registered listeners.
     */
    public void clear() {
        listeners.clear();
        LOGGER.debug("All event listeners cleared");
    }
}
