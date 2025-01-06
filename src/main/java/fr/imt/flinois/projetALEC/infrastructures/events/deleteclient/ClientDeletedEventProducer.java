package fr.imt.flinois.projetALEC.infrastructures.events.deleteclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDeletedEventProducer {
    private final ClientDeletedQueueService clientDeletedQueue;

    public ClientDeletedEventProducer(ClientDeletedQueueService clientDeletedQueue) {
        this.clientDeletedQueue = clientDeletedQueue;
    }

    // Sauvegarde l'id client dans un évènement, qui est ensuite ajouté à la file
    public void markClientAsDeleted(String clientId) {
        ClientDeletedEvent event = new ClientDeletedEvent(clientId);
        clientDeletedQueue.addToQueue(event);
        System.out.println("Événement ajouté à la file : Client " + clientId + " supprimé.");
    }
}
