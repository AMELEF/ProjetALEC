package fr.imt.flinois.projetALEC.business.domain.services;

import fr.imt.flinois.projetALEC.business.domain.models.Client;
import fr.imt.flinois.projetALEC.business.domain.models.Contrat;
import fr.imt.flinois.projetALEC.business.ports.inputs.ContratServicePort;
import fr.imt.flinois.projetALEC.business.ports.outputs.ContratRepositoryPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratServiceImpl implements ContratServicePort {
    private final ClientServiceImpl clientService;
    private final ContratRepositoryPort contratRepository;

    public ContratServiceImpl(ContratRepositoryPort contratRepository, ClientServiceImpl clientService) {
        this.contratRepository = contratRepository;
        this.clientService = clientService;
    }

    @Override
    public Contrat createContrat(@Valid Contrat contrat) {
        Optional<Client> client = clientService.findClientById(contrat.getClientId());
        if (client.isEmpty()) {
            throw new IllegalStateException("Le client avec l'ID " + contrat.getClientId() + " n'existe pas.");
        }
        return contratRepository.save(contrat);
    }

    @Override
    public Optional<Contrat> findContratById(String id) {
        return contratRepository.findById(id);
    }

    @Override
    public List<Contrat> findContratsByClientId(String clientId) {
        return contratRepository.findByClientId(clientId);
    }

    @Override
    public List<Contrat> findAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat updateContrat(String id, Contrat updatedContrat) {
        Optional<Contrat> existingContrat = contratRepository.findById(id);
        if (existingContrat.isEmpty()) {
            throw new IllegalStateException("Le contrat avec l'ID fourni n'existe pas.");
        }
        updatedContrat.setId(id);
        return contratRepository.save(updatedContrat);
    }

    @Override
    public void deleteContrat(String id) {
        Optional<Contrat> contrat = contratRepository.findById(id);
        if (contrat.isEmpty()) {
            throw new IllegalStateException("Le contrat avec l'ID " + id + " n'existe pas.");
        }
        contratRepository.deleteById(id);
    }

    @Override
    public void deleteByClientId(String id) {
        Optional<Client> client = clientService.findClientById(id);
        if (client.isEmpty()) {
            throw new IllegalStateException("Le client avec l'ID " + id + " n'existe pas.");
        }
        List<Contrat> contrats = findContratsByClientId(id);
        if (contrats.isEmpty()) {
            throw new IllegalStateException("Le client avec l'ID " + id + " n'a pas de contrat.");
        }
        contratRepository.deleteByClientId(id);
    }
}
