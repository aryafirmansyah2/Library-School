package kelompok7.library_school.services.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kelompok7.library_school.model.Jurnal;
import kelompok7.library_school.model.User;
import kelompok7.library_school.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // hash password
        return repository.save(user);
    }
    

    @Transactional
    public User update(Long id, User userData) {
        User existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        // Cek duplikat email
        if (userData.getEmail() != null && !userData.getEmail().equals(existing.getEmail())) {
            if (repository.existsByEmail(userData.getEmail())) {
                throw new RuntimeException("Email sudah digunakan oleh user lain");
            }
            existing.setEmail(userData.getEmail());
        }

        // Cek duplikat no_hp
        if (userData.getNoHp() != null && !userData.getNoHp().equals(existing.getNoHp())) {
            // Tambahkan method existsByNoHp di repository
            if (repository.existsByNoHp(userData.getNoHp())) {
                throw new RuntimeException("No HP sudah digunakan oleh user lain");
            }
            existing.setNoHp(userData.getNoHp());
        }

        if (userData.getKelas() != null)
            existing.setKelas(userData.getKelas());
        if (userData.getNamaDepan() != null)
            existing.setNamaDepan(userData.getNamaDepan());
        if (userData.getNamaBelakang() != null)
            existing.setNamaBelakang(userData.getNamaBelakang());
        if (userData.getRole() != null)
            existing.setRole(userData.getRole());
        if (userData.getPassword() != null && !userData.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(userData.getPassword())); // hash password
        }

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}