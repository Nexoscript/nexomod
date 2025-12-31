package com.nexoscript.nexomod.event.reflect;

import com.nexoscript.nexomod.event.api.EventBase;
import com.nexoscript.nexomod.event.api.EventPriority;
import com.nexoscript.nexomod.event.api.CancellableEvent;
import com.nexoscript.nexomod.event.api.reflect.IEventBus;
import com.nexoscript.nexomod.event.api.wrapper.IListenerWrapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class EventBus implements IEventBus {
    private final Map<Class<? extends EventBase>, List<IListenerWrapper>> listeners;
    private final ExecutorService asyncExecutor = Executors.newCachedThreadPool();

    public EventBus() {
        this.listeners = new ConcurrentHashMap<>();
    }

    @Override
    public void registerListener(Class<? extends EventBase> type, IListenerWrapper listenerWrapper) {
        listeners.computeIfAbsent(type, clazz -> new ArrayList<>()).add(listenerWrapper);
        listeners.get(type).sort(Comparator.comparing(IListenerWrapper::priority));
    }

    @Override
    public void unregisterAll(Object owner) {
        for (List<IListenerWrapper> listenerWrappers : listeners.values()) {
            listenerWrappers.removeIf(listenerWrapper -> listenerWrapper.owner() == owner);
        }
    }

    @Override
    public void post(EventBase event) {
        List<IListenerWrapper> listenerWrappers = listeners.get(event.getClass());
        if (listenerWrappers == null) {
            return;
        }
        for (IListenerWrapper listenerWrapper : listenerWrappers) {
            if (event instanceof CancellableEvent c && c.cancelled() && listenerWrapper.ignoreCancelled()) {
                return;
            }
            Runnable task = () -> {
                try {
                    listenerWrapper.invoke(event);
                } catch (Throwable throwable) {
                    System.err.println(throwable.getMessage());
                }
            };
            if (listenerWrapper.async()) {
                asyncExecutor.execute(task);
                continue;
            }
            task.run();
        }
    }
}
