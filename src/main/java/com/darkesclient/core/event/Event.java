package com.darkesclient.core.event;

/**
 * Base class for all events in the Darkes Client event system.
 * Events are fired through the EventBus and listeners can subscribe to them.
 */
public class Event {
    private boolean cancelled = false;

    /**
     * Cancels this event, preventing further processing.
     */
    public void cancel() {
        this.cancelled = true;
    }

    /**
     * Checks if this event has been cancelled.
     */
    public boolean isCancelled() {
        return cancelled;
    }
}
