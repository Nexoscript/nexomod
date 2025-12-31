package com.nexoscript.nexomod.test.nexomod.event.listener;

import com.nexoscript.nexomod.event.api.Event;
import com.nexoscript.nexomod.event.api.EventPriority;
import com.nexoscript.nexomod.event.api.concurrent.Async;
import com.nexoscript.nexomod.test.nexomod.event.event.ClientConnectEvent;

public class ConnectListener {

    @Async
    @Event(priority = EventPriority.HIGH)
    public void onConnect(ClientConnectEvent event) {
        System.out.println(event.clientId());
    }
}
