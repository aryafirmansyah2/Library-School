package kelompok7.library_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kelompok7.library_school.model.Peminjaman;
import kelompok7.library_school.model.User;
import kelompok7.library_school.model.Buku;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {

    // Cari semua peminjaman berdasarkan user
    List<Peminjaman> findByUser(User user);

    // Cari semua peminjaman yang sedang aktif (belum dikembalikan)
    List<Peminjaman> findBySudahDikembalikanFalse();

    // Cari semua peminjaman untuk satu buku tertentu
    List<Peminjaman> findByBuku(Buku buku);

    // Cari peminjaman aktif berdasarkan user dan buku (jika user sedang meminjam
    // buku tertentu)
    List<Peminjaman> findByUserAndBukuAndSudahDikembalikanFalse(User user, Buku buku);

    // Jumlah peminjaman aktif untuk satu buku (bisa dipakai untuk menghitung stok
    // tersisa)
    long countByBukuAndSudahDikembalikanFalse(Buku buku);
}
