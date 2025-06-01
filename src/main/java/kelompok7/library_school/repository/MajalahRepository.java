package kelompok7.library_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import kelompok7.library_school.model.Majalah;

public interface MajalahRepository extends JpaRepository<Majalah, Long> {
    List<Majalah> findByJudulContainingIgnoreCase(String judul);
}