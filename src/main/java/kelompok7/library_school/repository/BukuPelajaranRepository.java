package kelompok7.library_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kelompok7.library_school.model.BukuPelajaran;

public interface BukuPelajaranRepository extends JpaRepository<BukuPelajaran, Long> {
    List<BukuPelajaran> findByJudulContainingIgnoreCaseOrMaPelContainingIgnoreCase(String judul, String maPel);
}
