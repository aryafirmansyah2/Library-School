package kelompok7.library_school.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import kelompok7.library_school.config.JWTGenerator;
import kelompok7.library_school.dto.AuthResponseDTO;
import kelompok7.library_school.dto.LoginRequest;
import kelompok7.library_school.dto.RegisterRequest;
import kelompok7.library_school.model.User;
import kelompok7.library_school.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
            PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtGenerator.generateToken(authentication);

    User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Buat map untuk user
    Map<String, Object> userMap = new HashMap<>();
    userMap.put("id", user.getId());
    userMap.put("email", user.getEmail());
    userMap.put("namaDepan", user.getNamaDepan());
    userMap.put("namaBelakang", user.getNamaBelakang());
    userMap.put("role", user.getRole());
    userMap.put("kelas", user.getKelas());
    userMap.put("noHp", user.getNoHp());

    // Buat map utama untuk response
    Map<String, Object> response = new HashMap<>();
    response.put("accessToken", token);
    response.put("userId", user.getId()); // ✅ Tambahan: ID di root-level response
    response.put("user", userMap);

    return new ResponseEntity<>(response, HttpStatus.OK);
}


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setNamaDepan(registerRequest.getNamaDepan());
        user.setNamaBelakang(registerRequest.getNamaBelakang());
        user.setKelas(registerRequest.getKelas());
        user.setNoHp(registerRequest.getNoHP());
        user.setRole("USER");

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

}