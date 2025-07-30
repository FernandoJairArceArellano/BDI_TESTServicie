package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

//    @Lazy
    @Autowired
    public UserInfoService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    // Method to load user details by username (email)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Fetch user from the database by email (username)
//        Optional<Usuario> usuario = repository.findByUsername(username);
//
//        if (usuario.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with email: " + username);
//        }
//
//        // Convert UserInfo to UserDetails (UserInfoDetails)
//        Usuario user = usuario.get();
//        return new User(user.getUsername(), user.getPassword(),user.getAuthorities());
//    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserInfoDetails(usuario);
    }

    // Add any additional methods for registering or managing users
    public String addUser(Usuario usuario) {
        // Encrypt password before saving
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario);
        return "User added successfully!";
    }
}
