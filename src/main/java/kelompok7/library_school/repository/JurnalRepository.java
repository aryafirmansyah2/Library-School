package kelompok7.library_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import kelompok7.library_school.model.Jurnal;

public interface JurnalRepository extends JpaRepository<Jurnal, Long> {
    List<Jurnal> findByJudulContainingIgnoreCase(String judul);
}
