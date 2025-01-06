package fr.imt.flinois.projetALEC.interfaces.rest;

import fr.imt.flinois.projetALEC.business.domain.models.Contrat;
import fr.imt.flinois.projetALEC.business.ports.inputs.ContratServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contrats")
public class ContratController {
    private final ContratServicePort contratService;

    public ContratController(ContratServicePort contratService) {
        this.contratService = contratService;
    }

    @PostMapping
    public ResponseEntity<Contrat> createContrat(@RequestBody @Valid Contrat contrat) {
        return ResponseEntity.ok(contratService.createContrat(contrat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContratById(@PathVariable String id) {
        return contratService.findContratById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Contrat>> getContratsByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(contratService.findContratsByClientId(clientId));
    }

    @GetMapping
    public ResponseEntity<List<Contrat>> getAllContrats() {
        return ResponseEntity.ok(contratService.findAllContrats());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> updateContrat(@PathVariable String id, @RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.updateContrat(id, contrat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable String id) {
        contratService.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/client/{clientId}")
    public ResponseEntity<Void> deleteContractsByClientId(@PathVariable String clientId) {
        contratService.deleteByClientId(clientId);
        return ResponseEntity.noContent().build();
    }
}
