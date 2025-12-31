package com.nexoscript.nexomod.event.api.wrapper;

import com.nexoscript.nexomod.event.api.EventBase;
import com.nexoscript.nexomod.event.api.EventPriority;

import java.lang.reflect.Method;

public interface IListenerWrapper {
    void invoke(EventBase event) throws Throwable;
    Object owner();
    Method method();
    EventPriority priority();
    boolean ignoreCancelled();
    boolean async();
}
