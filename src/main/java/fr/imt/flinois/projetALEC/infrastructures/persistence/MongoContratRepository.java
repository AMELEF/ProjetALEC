package fr.imt.flinois.projetALEC.infrastructures.persistence;

import fr.imt.flinois.projetALEC.infrastructures.entities.ContratEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoContratRepository extends MongoRepository<ContratEntity, String> {
    /*
        On met ici les méthodes qui ne sont pas incluses nativement par la Mongo
        mais qui sont demandées par le domaine métier.
     */
    List<ContratEntity> findByClientId(String clientId);
    void deleteByClientId(String clientId);
}
