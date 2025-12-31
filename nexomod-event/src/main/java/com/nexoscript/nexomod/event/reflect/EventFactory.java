package com.nexoscript.nexomod.event.reflect;

import com.nexoscript.nexomod.event.api.Event;
import com.nexoscript.nexomod.event.api.EventBase;
import com.nexoscript.nexomod.event.api.concurrent.Async;
import com.nexoscript.nexomod.event.api.reflect.IEventBus;
import com.nexoscript.nexomod.event.api.reflect.IEventFactory;
import com.nexoscript.nexomod.event.wrapper.ListenerWrapper;

import java.lang.reflect.Method;

public final class EventFactory implements IEventFactory {
    private final IEventBus eventBus;

    public EventFactory(IEventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            Event event = method.getAnnotation(Event.class);
            if (event == null) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }
            if (!EventBase.class.isAssignableFrom(parameterTypes[0])) {
                continue;
            }
            boolean async = method.isAnnotationPresent(Async.class);
            Class<? extends EventBase> eventType = (Class<? extends EventBase>) parameterTypes[0];
            eventBus.registerListener(eventType, new ListenerWrapper(listener, method, event.priority(), event.ignoreCancelled(), async));
        }
    }

    @Override
    public void unregister(Object listener) {
        eventBus.unregisterAll(listener);
    }
}
