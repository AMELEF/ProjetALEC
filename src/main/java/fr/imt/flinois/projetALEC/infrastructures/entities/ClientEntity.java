package fr.imt.flinois.projetALEC.infrastructures.entities;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Document(collection = "clients")
public class ClientEntity {

    /*
        Les ClientEntities/ContratEntities ont exactement les mêmes attributs, donc ces objets ainsi
        que les mappers n'ont pas vraiment lieu d'être, à part pour respecter "les règles de l'art"
     */

    @Id
    private String id;
    private String name;
    private String email;

    public ClientEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
