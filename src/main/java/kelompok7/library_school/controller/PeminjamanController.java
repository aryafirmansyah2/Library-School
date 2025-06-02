package kelompok7.library_school.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import kelompok7.library_school.dto.BukuPinjamRequest;
import kelompok7.library_school.dto.PeminjamanRequest;
import kelompok7.library_school.dto.BukuStatusResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kelompok7.library_school.model.Buku;
import kelompok7.library_school.model.Peminjaman;
import kelompok7.library_school.model.User;
import kelompok7.library_school.services.peminjaman.PeminjamanService;

@RestController
@RequestMapping("/api/riwayat-peminjaman")
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    // Endpoint cek status buku (tersedia atau tidak) berdasarkan ID buku (dari
    // subclass Buku)
    @GetMapping("/status-buku/{bukuId}")
    public ResponseEntity<?> getStatusBuku(@PathVariable Long bukuId) {
        Optional<Buku> bukuOpt = peminjamanService.findBukuById(bukuId);
        if (bukuOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Buku tidak ditemukan");
        }
        Buku buku = bukuOpt.get();

        long jumlahDipinjam = peminjamanService.countAktifByBukuId(bukuId);
        int jumlahTersedia = buku.getJumlah();
        boolean tersedia = buku.isAvailable() && jumlahTersedia > 0;

        return ResponseEntity
                .ok(new BukuStatusResponse(buku.getId(), buku.getJudul(), tersedia, jumlahTersedia, jumlahDipinjam));
    }

    // Endpoint buat peminjaman baru (buku didapat dari service berdasarkan bukuId)
    @PostMapping
    public ResponseEntity<?> pinjamBanyakBuku(@RequestBody PeminjamanRequest request) {
        try {
            User user = new User();
            user.setId(request.getUserId());

            List<String> gagal = new ArrayList<>();
            List<Peminjaman> berhasil = new ArrayList<>();

            for (BukuPinjamRequest bpr : request.getBukuList()) {
                Optional<Buku> bukuOpt = peminjamanService.findBukuById(bpr.getBukuId());
                if (bukuOpt.isEmpty()) {
                    gagal.add("Buku ID " + bpr.getBukuId() + " tidak ditemukan");
                    continue;
                }

                Buku buku = bukuOpt.get();

                if (!buku.isAvailable() || buku.getJumlah() < bpr.getJumlah()) {
                    gagal.add("Buku '" + buku.getJudul() + "' tidak cukup tersedia. Diminta: " + bpr.getJumlah()
                            + ", tersedia: " + buku.getJumlah());
                    continue;
                }

                for (int i = 0; i < bpr.getJumlah(); i++) {
                    Peminjaman peminjaman = new Peminjaman();
                    peminjaman.setUser(user);
                    peminjaman.setBuku(buku);
                    berhasil.add(peminjamanService.create(peminjaman));
                }
            }

            if (berhasil.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                        "message", "Peminjaman gagal",
                        "gagal", gagal));
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Peminjaman berhasil");
            response.put("totalDipinjam", berhasil.size());
            if (!gagal.isEmpty()) {
                response.put("gagal", gagal);
            }
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Peminjaman gagal",
                    "error", e.getMessage()));
        }
    }

    // Endpoint kembalikan buku berdasarkan peminjamanId
    @PutMapping("/kembalikan/{peminjamanId}")
    public ResponseEntity<?> kembalikanBuku(@PathVariable Long peminjamanId) {
        try {
            // Jalankan service, tapi jangan kembalikan hasilnya
            peminjamanService.kembalikanBuku(peminjamanId);

            // Kembalikan hanya message sukses
            return ResponseEntity.ok(
                    Collections.singletonMap("message", "Buku berhasil dikembalikan."));
        } catch (RuntimeException e) {
            // Kembalikan hanya message gagal
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("message", "Gagal mengembalikan buku: " + e.getMessage()));
        }
    }

    @GetMapping()
    public List<Peminjaman> getPinjam() {
        return peminjamanService.getAll();
    }

    // Dapatkan semua peminjaman aktif
    @GetMapping("/aktif")
    public List<Peminjaman> getAktifPeminjaman() {
        return peminjamanService.getAktifPeminjaman();
    }

    // Dapatkan peminjaman berdasarkan userId
    @GetMapping("/user/{userId}")
    public List<Peminjaman> getByUser(@PathVariable Long userId) {
        return peminjamanService.getByUserId(userId);
    }

    // Dapatkan peminjaman berdasarkan bukuId
    @GetMapping("/buku/{bukuId}")
    public List<Peminjaman> getByBuku(@PathVariable Long bukuId) {
        return peminjamanService.getByBukuId(bukuId);
    }
}