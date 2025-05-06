package kelompok7.library_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kelompok7.library_school.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}