package fr.imt.flinois.projetALEC.infrastructures.mappers;

import fr.imt.flinois.projetALEC.business.domain.models.Client;
import fr.imt.flinois.projetALEC.infrastructures.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    /*
        Les ClientEntities/ContratEntities ont exactement les mêmes attributs, donc ces objets ainsi
        que les mappers n'ont pas vraiment lieu d'être, à part pour respecter "les règles de l'art"
     */
    public ClientEntity toEntity(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientEntity(
                client.getId(),
                client.getName(),
                client.getEmail()
        );
    }

    public Client toDomain(ClientEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Client(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}
