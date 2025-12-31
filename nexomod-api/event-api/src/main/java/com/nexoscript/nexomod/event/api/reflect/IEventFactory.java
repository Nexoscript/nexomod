package com.nexoscript.nexomod.event.api.reflect;

public interface IEventFactory {
    void register(Object listener);
    void unregister(Object listener);
}
