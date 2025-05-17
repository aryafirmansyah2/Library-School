package kelompok7.library_school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kelompok7.library_school.model.Majalah;
import kelompok7.library_school.services.MajalahService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/majalah")
public class MajalahController {

    @Autowired
    private MajalahService service;

    @GetMapping()
    public List<Majalah> getAllMajalah(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Majalah getMajalahById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    @PostMapping()
    public Majalah createMajalah(@RequestBody Majalah majalah) {
        return service.create(majalah);
    }

    @PutMapping("/{id}")
    public Majalah updateMajalah(@PathVariable Long id, @RequestBody Majalah majalah) {
        return service.update(id, majalah);
    }

    @DeleteMapping("/{id}")
    public void deleteMajalah(@PathVariable Long id) {
        service.delete(id);
    }
    
}
