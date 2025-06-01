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

import kelompok7.library_school.model.Majalah;
import kelompok7.library_school.services.majalah.MajalahService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/majalah")
public class MajalahController {

    @Autowired
    private MajalahService service;

    @GetMapping()
    public List<Majalah> getAllMajalah() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Majalah getMajalahById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Majalah> createMajalah(
            @RequestParam("cover") MultipartFile file,
            @RequestParam("judul") String judul,
            @RequestParam("penulis") String penulis,
            @RequestParam("penerbit") String penerbit,
            @RequestParam("tahunTerbit") String tahunTerbit,
            @RequestParam("deskripsi") String deskripsi,
            @RequestParam("halaman") int halaman,
            @RequestParam("jumlah") int jumlah,
            @RequestParam("edisi") String edisi,
            @RequestParam("frekuensiTerbit") String frekuensiTerbit) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = "public/uploads/majalah/" + fileName;
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            Majalah majalah = new Majalah();
            majalah.setJudul(judul);
            majalah.setPenulis(penulis);
            majalah.setPenerbit(penerbit);
            majalah.setTahunTerbit(tahunTerbit);
            majalah.setDeskripsi(deskripsi);
            majalah.setHalaman(halaman);
            majalah.setJumlah(jumlah);
            majalah.setAvailable(true);
            majalah.setEdisi(edisi);
            ;
            majalah.setFrekuensiTerbit(frekuensiTerbit);
            ;
            majalah.setCover("/uploads/majalah/" + fileName);

            Majalah saved = service.create(majalah);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<Majalah> updateMajalah(
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
            @RequestParam(value = "edisi", required = false) String edisi,
            @RequestParam(value = "frekuensiTerbit", required = false) String frekuensiTerbit) {
        try {
            // Ambil data lama dari DB
            Majalah existing = service.getById(id)
                    .orElseThrow(() -> new RuntimeException("Majalah tidak ditemukan"));

            // Jika ada cover baru, simpan dan hapus file lama
            if (coverFile != null && !coverFile.isEmpty()) {
                // Hapus file lama
                String oldCoverPath = existing.getCover(); // misal "/uploads/majalah/123.jpg"
                Path oldPath = Paths.get("public" + oldCoverPath);
                Files.deleteIfExists(oldPath);

                // Simpan file baru
                String newFileName = System.currentTimeMillis() + "_" + coverFile.getOriginalFilename();
                Path newFilePath = Paths.get("public/uploads/Majalah/" + newFileName);
                Files.createDirectories(newFilePath.getParent());
                Files.write(newFilePath, coverFile.getBytes());

                existing.setCover("/uploads/majalah/" + newFileName);
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
            ;
            if (frekuensiTerbit != null)
                existing.setFrekuensiTerbit(frekuensiTerbit);
            ;

            Majalah updated = service.create(existing);
            return ResponseEntity.ok(updated);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMajalah(@PathVariable Long id) {
        service.delete(id);
    }

}
