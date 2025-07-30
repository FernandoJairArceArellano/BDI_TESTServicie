package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.AuthRequest;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.Service.JwtService;
import com.BDI_TESTServicie.Service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserInfoService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Bienvenido este es un endpoint no seguro";
    }
    
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Usuario usuario) {
        return service.addUser(usuario);
    }

    // Se eliminó los controles de rol aquí ya que ya se gestionan en SeguridadConfig
    
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Solicitud de usuario inválido!");
        }
    }
}
