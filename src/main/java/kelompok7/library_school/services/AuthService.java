package kelompok7.library_school.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.dto.LoginRequest;
import kelompok7.library_school.dto.RegisterRequest;
import kelompok7.library_school.exception.EmailNotFoundException;
import kelompok7.library_school.model.User;
import kelompok7.library_school.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User register(RegisterRequest request) {
        User user = new User();
        user.setNama_depan(request.getNamaDepan());
        user.setNama_belakang(request.getNamaBelakang());
        user.setKelas(request.getKelas());
        user.setNo_hp(request.getNoHP());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // gunakan encoder di real app
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EmailNotFoundException("Email tidak ditemukan"));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Password salah");
        }
        return user;
    }
}
