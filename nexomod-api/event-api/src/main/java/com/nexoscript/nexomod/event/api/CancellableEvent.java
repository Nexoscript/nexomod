package com.nexoscript.nexomod.event.api;

public abstract class CancellableEvent extends EventBase {
    private boolean cancelled;

    public boolean cancelled() {
        return cancelled;
    }

    public void cancel() {
        cancelled = true;
    }
}
