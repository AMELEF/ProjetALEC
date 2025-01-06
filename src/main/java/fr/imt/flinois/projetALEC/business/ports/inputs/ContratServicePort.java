package fr.imt.flinois.projetALEC.business.ports.inputs;

import fr.imt.flinois.projetALEC.business.domain.models.Contrat;

import java.util.List;
import java.util.Optional;

public interface ContratServicePort {
    Contrat createContrat(Contrat contrat);
    Optional<Contrat> findContratById(String id);
    List<Contrat> findContratsByClientId(String clientId);
    List<Contrat> findAllContrats();
    Contrat updateContrat(String id, Contrat updatedContrat);
    void deleteContrat(String id);
    void deleteByClientId(String id);
}
