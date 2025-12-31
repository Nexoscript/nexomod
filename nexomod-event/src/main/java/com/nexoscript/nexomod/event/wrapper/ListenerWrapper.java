package com.nexoscript.nexomod.event.wrapper;
import com.nexoscript.nexomod.event.api.EventBase;
import com.nexoscript.nexomod.event.api.EventPriority;
import com.nexoscript.nexomod.event.api.wrapper.IListenerWrapper;

import java.lang.reflect.Method;

public record ListenerWrapper(Object owner, Method method, EventPriority priority, boolean ignoreCancelled, boolean async) implements IListenerWrapper {

    public ListenerWrapper(Object owner, Method method, EventPriority priority, boolean ignoreCancelled, boolean async) {
        this.owner = owner;
        this.method = method;
        this.priority = priority;
        this.ignoreCancelled = ignoreCancelled;
        this.async = async;
        this.method.setAccessible(true);
    }

    @Override
    public void invoke(EventBase event) throws Throwable {
        method.invoke(owner, event);
    }
}
