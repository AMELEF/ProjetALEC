package fr.imt.flinois.projetALEC.business.domain.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
public class Client {

    @Id
    private String id;

    @NotNull(message = "Le nom ne peut pas être nul")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String name;

    @Email(message = "Email non valide")
    private String email;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client() {
        this.id = UUID.randomUUID().toString();
    }

    public Client(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}


