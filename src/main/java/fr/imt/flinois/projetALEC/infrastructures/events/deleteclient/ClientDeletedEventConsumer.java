package fr.imt.flinois.projetALEC.infrastructures.events.deleteclient;

import fr.imt.flinois.projetALEC.infrastructures.entities.ContratEntity;
import fr.imt.flinois.projetALEC.infrastructures.persistence.MongoContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDeletedEventConsumer {
    private final ClientDeletedQueueService clientDeletedQueue;
    private final MongoContratRepository contratRepository;

    public ClientDeletedEventConsumer(MongoContratRepository contratRepository, ClientDeletedQueueService clientDeletedQueue) {
        this.contratRepository = contratRepository;
        this.clientDeletedQueue = clientDeletedQueue;
    }

    @Scheduled(fixedRate = 2000)
    // Traite l'événement et supprime les contrats associés au clientId
    public void consumeEvents() {
        while (!clientDeletedQueue.isEmpty()) {
            try {
                ClientDeletedEvent event = clientDeletedQueue.getFromQueue();
                if (event != null) {
                    deleteContractsForClient(event.getClientId());
                }
            } catch (Exception e) {
                System.err.println("Erreur lors du traitement d'un événement : " + e.getMessage());
            }
        }
    }

    // Supprimer les contrats associés au client
    private void deleteContractsForClient(String clientId) {
        List<ContratEntity> contrats = contratRepository.findByClientId(clientId);
        contratRepository.deleteAll(contrats);
        System.out.println("Contrats supprimés pour le client : " + clientId);
    }
}
