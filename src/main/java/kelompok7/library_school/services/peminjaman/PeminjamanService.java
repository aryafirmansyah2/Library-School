package kelompok7.library_school.services.peminjaman;

import java.util.List;
import java.util.Optional;

import kelompok7.library_school.model.Peminjaman;
import kelompok7.library_school.model.Buku;

public interface PeminjamanService {
    List<Peminjaman> getAll();

    Optional<Peminjaman> getById(Long id);

    Peminjaman create(Peminjaman peminjaman);

    void delete(Long id);

    List<Peminjaman> getByUserId(Long userId);

    List<Peminjaman> getByBukuId(Long bukuId);

    List<Peminjaman> getAktifPeminjaman();

    long countAktifByBukuId(Long bukuId);

    Peminjaman kembalikanBuku(Long peminjamanId);

    Optional<Buku> findBukuById(Long id);

}
