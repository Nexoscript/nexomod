package com.nexoscript.nexomod.test.nexomod.event;

import com.nexoscript.nexomod.event.api.reflect.IEventBus;
import com.nexoscript.nexomod.event.api.reflect.IEventFactory;
import com.nexoscript.nexomod.event.reflect.EventBus;
import com.nexoscript.nexomod.event.reflect.EventFactory;
import com.nexoscript.nexomod.test.nexomod.event.event.ClientConnectEvent;
import com.nexoscript.nexomod.test.nexomod.event.listener.ConnectListener;

import java.util.UUID;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        IEventBus eventBus = new EventBus();
        IEventFactory eventFactory = new EventFactory(eventBus);
        eventFactory.register(new ConnectListener());
        Thread.sleep(1000);
        eventBus.post(new ClientConnectEvent(UUID.randomUUID().toString()));
    }
}
