package unsm.archivo.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import unsm.archivo.repository.UsuarioRepo;
import unsm.archivo.request.AuthResponse;
import unsm.archivo.request.LoginRequest;
import unsm.archivo.entitys.Usuario;

@Service
public class AuthService {
    private static final int MAX_FAILED_ATTEMPTS = 3;

    private final UsuarioRepo usuario;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepo usuario, JwtService jwtService, AuthenticationManager authenticationManager) {
        super();
        this.usuario = usuario;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usua = usuario.findByUsername(request.getUsername()).orElseThrow();

        // Verificar si la cuenta está inactiva
        if (usua.getEstado().equals("Inactivo")) {
            throw new RuntimeException("Cuenta inactiva.");
        }

        // Verificar si la cuenta está bloqueada
        if (!usua.getAccountnonlocked()) {
            throw new LockedException(
                    "Cuenta bloqueada debido a múltiples intentos fallidos. Contacte al administrador.");
        }

        try {
            // Autenticar al usuario
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()));

            // Reiniciar los intentos fallidos si la autenticación es exitosa
            if (usua.getIntentosfallidos() > 0) {
                usua.setIntentosfallidos(0);
                usuario.save(usua);
            }

            // Generar el token JWT
            String token = jwtService.getToken((UserDetails) usua);

            // Obtener el rol del usuario
            String role = usua.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_USER");

            // Preparar la respuesta de autenticación
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            authResponse.setRole(role);
            authResponse.setUsername(usua.getUsername());

            return authResponse;

        } catch (BadCredentialsException e) {
            int failedAttempts = usua.getIntentosfallidos() + 1;
            usua.setIntentosfallidos(failedAttempts);

            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                usua.setAccountnonlocked(false);
            }

            usuario.save(usua);

            throw new BadCredentialsException("Credenciales incorrectas");
        }
    }
}
