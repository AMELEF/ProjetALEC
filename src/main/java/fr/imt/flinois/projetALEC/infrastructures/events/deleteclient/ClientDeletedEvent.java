package fr.imt.flinois.projetALEC.infrastructures.events.deleteclient;

import lombok.Getter;

@Getter
public class ClientDeletedEvent {
    private String clientId;

    public ClientDeletedEvent(String clientId) {
        this.clientId = clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}

