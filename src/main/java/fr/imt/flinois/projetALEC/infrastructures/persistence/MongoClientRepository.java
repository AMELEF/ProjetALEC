package fr.imt.flinois.projetALEC.infrastructures.persistence;

import fr.imt.flinois.projetALEC.business.domain.models.Client;
import fr.imt.flinois.projetALEC.infrastructures.entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoClientRepository extends MongoRepository<ClientEntity, String> {
    /*
        On met ici les méthodes qui ne sont pas incluses nativement par la Mongo
        mais qui sont demandées par le domaine métier.
     */
    Optional<ClientEntity> findByEmail(String email);
}
