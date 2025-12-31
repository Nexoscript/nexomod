package com.nexoscript.nexomod.test.nexomod.event.event;

import com.nexoscript.nexomod.event.api.CancellableEvent;

public class ClientConnectEvent extends CancellableEvent {
    private final String clientId;

    public ClientConnectEvent(String clientId) {
        this.clientId = clientId;
    }

    public String clientId() {
        return clientId;
    }
}
