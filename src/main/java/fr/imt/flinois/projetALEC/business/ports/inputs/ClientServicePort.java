package fr.imt.flinois.projetALEC.business.ports.inputs;

import fr.imt.flinois.projetALEC.business.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServicePort {
    Client createClient(Client client);
    Optional<Client> findClientById(String id);
    List<Client> findAllClients();
    Optional<Client> findClientByEmail(String email);
    Client updateClient(String id, Client updatedClient);
    void deleteClient(String id);
}
