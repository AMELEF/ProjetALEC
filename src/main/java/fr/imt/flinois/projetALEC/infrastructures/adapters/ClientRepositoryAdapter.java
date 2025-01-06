package fr.imt.flinois.projetALEC.infrastructures.adapters;

import fr.imt.flinois.projetALEC.business.domain.models.Client;
import fr.imt.flinois.projetALEC.business.ports.outputs.ClientRepositoryPort;
import fr.imt.flinois.projetALEC.infrastructures.entities.ClientEntity;
import fr.imt.flinois.projetALEC.infrastructures.mappers.ClientMapper;
import fr.imt.flinois.projetALEC.infrastructures.persistence.MongoClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClientRepositoryAdapter implements ClientRepositoryPort {
    private final MongoClientRepository clientMongoRepository;
    private final ClientMapper clientMapper;
    

    public ClientRepositoryAdapter(MongoClientRepository clientMongoRepository, ClientMapper clientMapper) {
        this.clientMongoRepository = clientMongoRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = clientMapper.toEntity(client);
        ClientEntity savedEntity = clientMongoRepository.save(clientEntity);
        return clientMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Client> findById(String id) {
        Optional<ClientEntity> clientEntity = clientMongoRepository.findById(id);
        return clientEntity.map(clientMapper::toDomain);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        Optional<ClientEntity> clientEntity = clientMongoRepository.findByEmail(email);
        return clientEntity.map(clientMapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        // Récupérer tous les clients depuis la mongodb
        List<ClientEntity> clientEntities = clientMongoRepository.findAll();
        // Mapping
        return clientEntities.stream()
                .map(clientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        // Supprimer un Client par ID dans la mongodb
        clientMongoRepository.deleteById(id);
    }
}
