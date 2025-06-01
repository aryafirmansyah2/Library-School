package kelompok7.library_school.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import kelompok7.library_school.dto.PeminjamanRequest;

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
    public ResponseEntity<?> pinjamBuku(@RequestBody PeminjamanRequest request) {
        try {
            Optional<Buku> bukuOpt = peminjamanService.findBukuById(request.getBukuId());
            if (bukuOpt.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Buku tidak ditemukan"));
            }

            Buku buku = bukuOpt.get();
            if (!buku.isAvailable() || buku.getJumlah() < request.getJumlahPeminjaman()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Jumlah buku tidak mencukupi"));
            }

            List<Peminjaman> peminjamanList = new ArrayList<>();
            for (int i = 0; i < request.getJumlahPeminjaman(); i++) {
                Peminjaman peminjaman = new Peminjaman();
                User user = new User();
                user.setId(request.getUserId());
                peminjaman.setUser(user);
                peminjaman.setBuku(buku);

                peminjamanList.add(peminjamanService.create(peminjaman));
            }

            return ResponseEntity.ok(Map.of("message", "Peminjaman berhasil"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Peminjaman gagal: " + e.getMessage()));
        }
    }

    // Endpoint kembalikan buku berdasarkan peminjamanId
    @PutMapping("/kembalikan/{peminjamanId}")
    public ResponseEntity<?> kembalikanBuku(@PathVariable Long peminjamanId) {
        try {
            Peminjaman updated = peminjamanService.kembalikanBuku(peminjamanId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
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

    // DTO response untuk status buku
    private static class BukuStatusResponse {
        private Long id;
        private String judul;
        private boolean tersedia;
        private int jumlahTersedia;
        private long jumlahDipinjam;

        public BukuStatusResponse(Long id, String judul, boolean tersedia, int jumlahTersedia, long jumlahDipinjam) {
            this.id = id;
            this.judul = judul;
            this.tersedia = tersedia;
            this.jumlahTersedia = jumlahTersedia;
            this.jumlahDipinjam = jumlahDipinjam;
        }

        public Long getId() {
            return id;
        }

        public String getJudul() {
            return judul;
        }

        public boolean isTersedia() {
            return tersedia;
        }

        public int getJumlahTersedia() {
            return jumlahTersedia;
        }

        public long getJumlahDipinjam() {
            return jumlahDipinjam;
        }
    }
}
