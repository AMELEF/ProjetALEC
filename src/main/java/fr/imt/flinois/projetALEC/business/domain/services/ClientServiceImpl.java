package fr.imt.flinois.projetALEC.business.domain.services;

import fr.imt.flinois.projetALEC.business.domain.models.Client;
import fr.imt.flinois.projetALEC.business.ports.inputs.ClientServicePort;
import fr.imt.flinois.projetALEC.business.ports.outputs.ClientRepositoryPort;
import fr.imt.flinois.projetALEC.infrastructures.events.deleteclient.ClientDeletedEvent;
import fr.imt.flinois.projetALEC.infrastructures.events.deleteclient.ClientDeletedEventProducer;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientServicePort {
    private final ClientRepositoryPort clientRepository;
    private final ClientDeletedEventProducer eventProducer;

    public ClientServiceImpl(ClientRepositoryPort clientRepository,
                             ApplicationEventPublisher eventPublisher,
                             ClientDeletedEventProducer eventProducer) {
        this.clientRepository = clientRepository;
        //this.eventPublisher = eventPublisher;
        this.eventProducer = eventProducer;
    }

    @Override
    public Client createClient(@Valid Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Un client avec cet email existe déjà.");
        }
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findClientById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Client updateClient(String id, @Valid Client updatedClient) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isEmpty()) {
            throw new IllegalStateException("Le client avec l'ID fourni n'existe pas.");
        }
        updatedClient.setId(id);
        return clientRepository.save(updatedClient);
    }

    @Override
    public void deleteClient(String clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()) {
            throw new IllegalStateException("Le client avec l'ID " + clientId + " n'existe pas.");
        }

        // Supprimer le client
        eventProducer.markClientAsDeleted(clientId);
        clientRepository.deleteById(clientId);
    }
}
