package kelompok7.library_school.config;

import kelompok7.library_school.model.User;
import kelompok7.library_school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Spring Security will call this method during login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        // Return Spring Security's User object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()) // atau getUsername() jika itu identitasnya
                .password(user.getPassword())
                .roles(user.getRole()) // pastikan ini berupa String seperti "USER", "ADMIN"
                .build();
    }
}
