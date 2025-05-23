package kelompok7.library_school.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.model.User;
import kelompok7.library_school.repository.UserRepository;

@Service
public class UserService{
    @Autowired
    private UserRepository  repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(Long id, User userData) {
    return repository.findById(id).map(user -> {
        user.setNama_depan(userData.getNama_depan());
        user.setNama_belakang(userData.getNama_belakang());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setKelas(userData.getKelas());
        user.setNo_hp(userData.getNo_hp());
        user.setRole(userData.getRole());
        return repository.save(user);
    }).orElseThrow();
}

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
}