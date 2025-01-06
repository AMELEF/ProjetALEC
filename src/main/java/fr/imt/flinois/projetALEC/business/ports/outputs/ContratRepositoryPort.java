package fr.imt.flinois.projetALEC.business.ports.outputs;

import fr.imt.flinois.projetALEC.business.domain.models.Contrat;

import java.util.List;
import java.util.Optional;

public interface ContratRepositoryPort {
    Contrat save(Contrat contrat);

    Optional<Contrat> findById(String id);

    List<Contrat> findByClientId(String clientId);

    List<Contrat> findAll();

    void deleteById(String id);

    void deleteByClientId(String clientId);
}
