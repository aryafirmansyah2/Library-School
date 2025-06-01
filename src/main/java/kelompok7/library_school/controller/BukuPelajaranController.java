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

import kelompok7.library_school.model.BukuPelajaran;
import kelompok7.library_school.services.buku_pelajaran.BukuPelajaranService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/buku-pelajaran")
public class BukuPelajaranController {

    @Autowired
    private BukuPelajaranService service;

    @GetMapping()
    public List<BukuPelajaran> getBukuPelajaran(
            @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return service.getAll();
        } else {
            return service.searchByKeyword(keyword);
        }
    }

    @GetMapping("/{id}")
    public BukuPelajaran getBukuPelajaranById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<BukuPelajaran> createBukuPelajaran(
            @RequestParam("cover") MultipartFile file,
            @RequestParam("judul") String judul,
            @RequestParam("penulis") String penulis,
            @RequestParam("penerbit") String penerbit,
            @RequestParam("tahunTerbit") String tahunTerbit,
            @RequestParam("deskripsi") String deskripsi,
            @RequestParam("halaman") int halaman,
            @RequestParam("jumlah") int jumlah,
            @RequestParam("maPel") String maPel,
            @RequestParam("tingkatKelas") String tingkatKelas,
            @RequestParam("kurikulum") String kurikulum) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = "public/uploads/buku_pelajaran/" + fileName;
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            BukuPelajaran buku = new BukuPelajaran();
            buku.setJudul(judul);
            buku.setPenulis(penulis);
            buku.setPenerbit(penerbit);
            buku.setTahunTerbit(tahunTerbit);
            buku.setDeskripsi(deskripsi);
            buku.setHalaman(halaman);
            buku.setJumlah(jumlah);
            buku.setAvailable(true);
            buku.setMaPel(maPel);
            buku.setTingkatKelas(tingkatKelas);
            buku.setKurikulum(kurikulum);
            buku.setCover("/uploads/buku_pelajaran/" + fileName);

            BukuPelajaran saved = service.create(buku);
            return ResponseEntity
                    .ok()
                    .header("Content-Type", "application/json")
                    .body(saved);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<BukuPelajaran> updateBukuPelajaran(
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
            @RequestParam(value = "maPel", required = false) String maPel,
            @RequestParam(value = "tingkatKelas", required = false) String tingkatKelas,
            @RequestParam(value = "kurikulum", required = false) String kurikulum) {
        try {
            // Ambil data lama dari DB
            BukuPelajaran existing = service.getById(id)
                    .orElseThrow(() -> new RuntimeException("BukuPelajaran tidak ditemukan"));

            // Jika ada cover baru, simpan dan hapus file lama
            if (coverFile != null && !coverFile.isEmpty()) {
                // Hapus file lama
                String oldCoverPath = existing.getCover(); // misal "/uploads/buku_pelajaran/123.jpg"
                Path oldPath = Paths.get("public" + oldCoverPath);
                Files.deleteIfExists(oldPath);

                // Simpan file baru
                String newFileName = System.currentTimeMillis() + "_" + coverFile.getOriginalFilename();
                Path newFilePath = Paths.get("public/uploads/buku_pelajaran/" + newFileName);
                Files.createDirectories(newFilePath.getParent());
                Files.write(newFilePath, coverFile.getBytes());

                existing.setCover("/uploads/buku_pelajaran/" + newFileName);
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
            if (maPel != null)
                existing.setMaPel(maPel);
            if (tingkatKelas != null)
                existing.setTingkatKelas(tingkatKelas);
            if (kurikulum != null)
                existing.setKurikulum(kurikulum);

            BukuPelajaran updated = service.create(existing);
            return ResponseEntity.ok(updated);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBukuPelajaran(@PathVariable Long id) {
        service.delete(id);
    }

}
