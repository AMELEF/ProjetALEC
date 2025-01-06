package fr.imt.flinois.projetALEC.business.ports.outputs;

import fr.imt.flinois.projetALEC.business.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryPort {

    Client save(Client client);

    Optional<Client> findById(String id);

    List<Client> findAll();

    Optional<Client> findByEmail(String email);

    void deleteById(String id);
}
