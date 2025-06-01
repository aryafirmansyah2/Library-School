package kelompok7.library_school.services.buku_pelajaran;

import java.util.List;
import java.util.Optional;

import kelompok7.library_school.model.BukuPelajaran;

public interface BukuPelajaranService {
    List<BukuPelajaran> getAll();

    Optional<BukuPelajaran> getById(Long id);

    BukuPelajaran create(BukuPelajaran buku);

    void delete(Long id);

    List<BukuPelajaran> searchByKeyword(String keyword);
}
