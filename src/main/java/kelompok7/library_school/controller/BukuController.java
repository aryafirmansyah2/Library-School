package kelompok7.library_school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kelompok7.library_school.model.Buku;
import kelompok7.library_school.services.BukuService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/buku")
public class BukuController {
    @Autowired
    private BukuService service;

    @GetMapping()
    public List<Buku> getAllBuku() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Buku getBukuById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    @PostMapping()
    public Buku createBuku(@RequestBody Buku buku) {
        return service.create(buku);
    }

    @PutMapping("/{id}")
    public Buku updateBuku(@PathVariable Long id, @RequestBody Buku buku) {
        return service.update(id, buku);
    }

    @DeleteMapping("/{id}")
    public void deleteBuku(@PathVariable Long id) {
        service.delete(id);
    }
}
