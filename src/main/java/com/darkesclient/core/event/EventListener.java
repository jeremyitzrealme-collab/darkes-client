package com.darkesclient.core.event;

/**
 * Functional interface for event listeners.
 * Implement this to handle events from the EventBus.
 */
@FunctionalInterface
public interface EventListener<T extends Event> {
    /**
     * Called when an event of type T is fired.
     * @param event The event that was fired
     */
    void onEvent(T event);
}
