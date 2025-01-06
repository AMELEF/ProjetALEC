package fr.imt.flinois.projetALEC.infrastructures.entities;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Document(collection = "contrats")
public class ContratEntity {
    /*
        Les ClientEntities/ContratEntities ont exactement les mêmes attributs, donc ces objets ainsi
        que les mappers n'ont pas vraiment lieu d'être, à part pour respecter "les règles de l'art"
     */
    @Id
    private String id;
    private String clientId;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;

    public ContratEntity(String id, String clientId, String description, LocalDate dateDebut, LocalDate dateFin, String statut) {
        this.id = id;
        this.clientId = clientId;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
