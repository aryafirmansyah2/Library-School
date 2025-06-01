package kelompok7.library_school.controller;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> pinjamBuku(@RequestParam Long userId, @RequestParam Long bukuId) {
        try {
            // Cari buku dari service, bisa BukuPelajaran, Majalah, atau Jurnal
            Optional<Buku> bukuOpt = peminjamanService.findBukuById(bukuId);
            if (bukuOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Buku tidak ditemukan");
            }
            Buku buku = bukuOpt.get();

            // Buat instance Peminjaman baru
            Peminjaman peminjaman = new Peminjaman();

            // Set user (hanya set id, asumsikan User sudah ada di DB)
            User user = new User();
            user.setId(userId);
            peminjaman.setUser(user);

            // Set buku dari hasil pencarian
            peminjaman.setBuku(buku);

            // Simpan peminjaman via service (akan handle pengurangan stok buku)
            Peminjaman created = peminjamanService.create(peminjaman);

            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
