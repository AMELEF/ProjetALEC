package fr.imt.flinois.projetALEC.business.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Contrat {
    @Id
    private String id;
    private String clientId;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;
    private String statut;

    public Contrat(){
        this.id = UUID.randomUUID().toString();
    }

    public Contrat(String id, String clientId, String description, LocalDate dateDebut, LocalDate dateFin, String statut) {
        this.id = id;
        this.clientId = clientId;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }

    public Contrat(String clientId, String description, LocalDate dateDebut, LocalDate dateFin) {
        this.id = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = getStatut();
    }

    public String getStatut() {
        if(LocalDate.now().isAfter(this.dateFin)){
            this.statut = Statut.EXPIRE.text;
            return this.statut;
        }
        if (LocalDate.now().isBefore(this.dateDebut)){
            this.statut = Statut.PLANIFIE.text;
            return this.statut;
        }
        this.statut = Statut.ACTIF.text;
        return this.statut;
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


    private enum Statut {
        ACTIF("Actif"), EXPIRE("Expiré"), PLANIFIE("Planifié");
        private final String text;

        Statut(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
