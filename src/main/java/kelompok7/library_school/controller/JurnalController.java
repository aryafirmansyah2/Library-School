package kelompok7.library_school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kelompok7.library_school.model.Jurnal;
import kelompok7.library_school.services.JurnalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/jurnals")
public class JurnalController {

    @Autowired
    private JurnalService service;

    // Get all journals
    @GetMapping()
    public List<Jurnal> getAllJurnals() {
        return service.getAll();
    }

    // Get journal by ID
    @GetMapping("/{id}")
    public Jurnal getJurnalById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    // Create new journal
    @PostMapping()
    public Jurnal createJurnal(@RequestBody Jurnal jurnal) {
        return service.create(jurnal); // Delegating the validation to service
    }

    // Update journal
    @PutMapping("/{id}")
    public Jurnal updateJurnal(@PathVariable Long id, @RequestBody Jurnal jurnal) {
        return service.update(id, jurnal); // Delegating the validation to service
    }

    // Delete journal
    @DeleteMapping("/{id}")
    public void deleteJurnal(@PathVariable Long id) {
        service.delete(id);
    }
}
