package kelompok7.library_school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kelompok7.library_school.model.BukuPelajaran;
import kelompok7.library_school.services.BukuPelajaranService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/buku-pelajaran")
public class BukuPelajaranController {

    @Autowired
    private BukuPelajaranService service;

    @GetMapping()
    public List<BukuPelajaran> getAllBukuPelajarans(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BukuPelajaran getBukuPelajaranById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    @PostMapping()
    public BukuPelajaran creatBukuPelajaran(@RequestBody BukuPelajaran bukuPelajaran) {
        return service.create(bukuPelajaran);
    }

    @PutMapping("/{id}")
    public BukuPelajaran updateBukuPelajaran(@PathVariable Long id, @RequestBody BukuPelajaran bukuPelajaran) {
        return service.update(id, bukuPelajaran);
    }

    @DeleteMapping("/{id}")
    public void deleteBukuPelajaran(@PathVariable Long id) {
        service.delete(id);
    }
    



    
}
