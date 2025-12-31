package com.nexoscript.nexomod.event.api.reflect;

import com.nexoscript.nexomod.event.api.EventBase;
import com.nexoscript.nexomod.event.api.wrapper.IListenerWrapper;

public interface IEventBus {
    void registerListener(Class<? extends EventBase> type, IListenerWrapper wrapper);
    void unregisterAll(Object owner);
    void post(EventBase event);
}
