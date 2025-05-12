package kelompok7.library_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kelompok7.library_school.model.Buku;


public interface BukuRepository extends JpaRepository<Buku, Long> {
}
