package fr.imt.flinois.projetALEC.infrastructures.adapters;

import fr.imt.flinois.projetALEC.business.domain.models.Contrat;
import fr.imt.flinois.projetALEC.business.ports.outputs.ContratRepositoryPort;
import fr.imt.flinois.projetALEC.infrastructures.entities.ContratEntity;
import fr.imt.flinois.projetALEC.infrastructures.mappers.ContratMapper;
import fr.imt.flinois.projetALEC.infrastructures.persistence.MongoContratRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ContratRepositoryAdapter implements ContratRepositoryPort {
    private final MongoContratRepository contratMongoRepository;
    private final ContratMapper contratMapper;

    public ContratRepositoryAdapter(MongoContratRepository contratMongoRepository, ContratMapper contratMapper) {
        this.contratMongoRepository = contratMongoRepository;
        this.contratMapper = contratMapper;
    }

    @Override
    public Contrat save(Contrat contrat) {
        // Mapper le modèle Contrat en ContratEntity
        ContratEntity contratEntity = contratMapper.toEntity(contrat);
        // Sauvegarder dans MongoDB
        ContratEntity savedEntity = contratMongoRepository.save(contratEntity);
        // Retourner le modèle Contrat
        return contratMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Contrat> findById(String id) {
        // Trouver une entité dans MongoDB par son ID
        Optional<ContratEntity> contratEntity = contratMongoRepository.findById(id);
        // Mapper l'entité en modèle et retourner
        return contratEntity.map(contratMapper::toDomain);
    }

    @Override
    public List<Contrat> findByClientId(String clientId) {
        // Trouver toutes les entités liées au client dans MongoDB
        List<ContratEntity> contratEntities = contratMongoRepository.findByClientId(clientId);
        // Mapper la liste d'entités en liste de modèles
        return contratEntities.stream()
                .map(contratMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Contrat> findAll() {
        // Récupérer toutes les entités dans MongoDB
        List<ContratEntity> contratEntities = contratMongoRepository.findAll();
        // Mapper la liste d'entités en liste de modèles
        return contratEntities.stream()
                .map(contratMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        // Supprimer une entité dans MongoDB par son ID
        contratMongoRepository.deleteById(id);
    }

    @Override
    public void deleteByClientId(String clientId) {
        contratMongoRepository.deleteByClientId(clientId);
    }

}
