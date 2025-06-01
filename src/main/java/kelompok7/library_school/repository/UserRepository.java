package kelompok7.library_school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kelompok7.library_school.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByNoHp (String no_hp);

}