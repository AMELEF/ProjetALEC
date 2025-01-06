package fr.imt.flinois.projetALEC.infrastructures.mappers;

import fr.imt.flinois.projetALEC.business.domain.models.Contrat;
import fr.imt.flinois.projetALEC.infrastructures.entities.ContratEntity;
import org.springframework.stereotype.Component;

@Component
public class ContratMapper {
    /*
        Les ClientEntities/ContratEntities ont exactement les mêmes attributs, donc ces objets ainsi
        que les mappers n'ont pas vraiment lieu d'être, à part pour respecter "les règles de l'art"
     */
    public ContratEntity toEntity(Contrat contrat) {
        if (contrat == null) {
            return null;
        }
        return new ContratEntity(
                contrat.getId(),
                contrat.getClientId(),
                contrat.getDescription(),
                contrat.getDateDebut(),
                contrat.getDateFin(),
                contrat.getStatut()
        );
    }

    public Contrat toDomain(ContratEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Contrat(
                entity.getId(),
                entity.getClientId(),
                entity.getDescription(),
                entity.getDateDebut(),
                entity.getDateFin(),
                entity.getStatut()
        );
    }
}
