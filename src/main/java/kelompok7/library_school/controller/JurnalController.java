package kelompok7.library_school.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kelompok7.library_school.model.Jurnal;
import kelompok7.library_school.services.jurnal.JurnalService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/jurnal")
public class JurnalController {

    @Autowired
    private JurnalService service;

    // Get all journals
    @GetMapping()
    public List<Jurnal> getJurnal(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return service.getAll();
        } else {
            return service.searchByJudul(keyword);
        }
    }


    // Get journal by ID
    @GetMapping("/{id}")
    public Jurnal getJurnalById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Jurnal> createJurnal(
            @RequestParam("cover") MultipartFile file,
            @RequestParam("judul") String judul,
            @RequestParam("penulis") String penulis,
            @RequestParam("penerbit") String penerbit,
            @RequestParam("tahunTerbit") String tahunTerbit,
            @RequestParam("deskripsi") String deskripsi,
            @RequestParam("halaman") int halaman,
            @RequestParam("jumlah") int jumlah,
            @RequestParam("edisi") String edisi,
            @RequestParam("frekuensiTerbit") String frekuensiTerbit,
            @RequestParam("bidang") String bidang,
            @RequestParam("volume") String volume) {
        try {

            if (file.getSize() > 2 * 1024 * 1024) { // 2MB
                return ResponseEntity.badRequest().body(null); // Bisa ubah dengan pesan error
            }
            
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("public", "uploads", "jurnal");
            Files.createDirectories(uploadPath);
            Path fullPath = uploadPath.resolve(fileName);
            Files.write(fullPath, file.getBytes());

            Jurnal jurnal = new Jurnal();
            jurnal.setJudul(judul);
            jurnal.setPenulis(penulis);
            jurnal.setPenerbit(penerbit);
            jurnal.setTahunTerbit(tahunTerbit);
            jurnal.setDeskripsi(deskripsi);
            jurnal.setHalaman(halaman);
            jurnal.setJumlah(jumlah);
            jurnal.setAvailable(true);
            jurnal.setEdisi(edisi);
            jurnal.setFrekuensiTerbit(frekuensiTerbit);
            jurnal.setBidang(bidang);
            jurnal.setVolume(volume);
            jurnal.setCover("/uploads/jurnal/" + fileName);

            Jurnal saved = service.create(jurnal);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Update journal
    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<Jurnal> updateJurnal(
        @PathVariable Long id, 
        @RequestParam(value = "cover", required = false) MultipartFile coverFile,
        @RequestParam(value = "judul", required = false) String judul,
        @RequestParam(value = "penulis", required = false) String penulis,
        @RequestParam(value = "penerbit", required = false) String penerbit,
        @RequestParam(value = "tahunTerbit", required = false) String tahunTerbit,
        @RequestParam(value = "deskripsi", required = false) String deskripsi,
        @RequestParam(value = "halaman", required = false) Integer halaman,
        @RequestParam(value = "jumlah", required = false) Integer jumlah,
        @RequestParam(value = "available", required = false) Boolean available,
        @RequestParam(value = "edisi",required = false) String edisi,
        @RequestParam(value = "frekuensiTerbit",required = false) String frekuensiTerbit,
        @RequestParam(value = "bidang",required = false) String bidang,
        @RequestParam(value = "volume",required = false) String volume) {
            try {
                // Ambil data lama dari DB
                Jurnal existing = service.getById(id)
                        .orElseThrow(() -> new RuntimeException("Jurnal tidak ditemukan"));
    
                // Jika ada cover baru, simpan dan hapus file lama
                if (coverFile != null && !coverFile.isEmpty()) {
                    // Hapus file lama
                    String oldCoverPath = existing.getCover(); // misal "/uploads/jurnal/123.jpg"
                    Path oldPath = Paths.get("public" + oldCoverPath);
                    Files.deleteIfExists(oldPath);
    
                    // Simpan file baru
                    String newFileName = System.currentTimeMillis() + "_" + coverFile.getOriginalFilename();
                    Path newFilePath = Paths.get("public/uploads/jurnal/" + newFileName);
                    Files.createDirectories(newFilePath.getParent());
                    Files.write(newFilePath, coverFile.getBytes());
    
                    existing.setCover("/uploads/jurnal/" + newFileName);
                }
    
                // Update field lain jika tidak null
                if (judul != null)
                    existing.setJudul(judul);
                if (penulis != null)
                    existing.setPenulis(penulis);
                if (penerbit != null)
                    existing.setPenerbit(penerbit);
                if (tahunTerbit != null)
                    existing.setTahunTerbit(tahunTerbit);
                if (deskripsi != null)
                    existing.setDeskripsi(deskripsi);
                if (halaman != null)
                    existing.setHalaman(halaman);
                if (jumlah != null)
                    existing.setJumlah(jumlah);
                if (available != null)
                    existing.setAvailable(available);
                if (edisi != null)
                    existing.setEdisi(edisi);
                if (frekuensiTerbit != null)
                    existing.setFrekuensiTerbit(frekuensiTerbit);
                if (bidang != null)
                    existing.setBidang(bidang);
                if (volume != null)
                    existing.setVolume(volume);
    
                Jurnal updated = service.create(existing);
                return ResponseEntity.ok(updated);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();
            }
        }

    // Delete journal
    @DeleteMapping("/{id}")
    public void deleteJurnal(@PathVariable Long id) {
        service.delete(id);
    }
}
