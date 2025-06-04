package unsm.archivo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import unsm.archivo.request.AuthResponse;
import unsm.archivo.request.LoginRequest;
import unsm.archivo.services.AuthService;

import unsm.archivo.repository.UsuarioRepo;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private final AuthService authService;
    private final UsuarioRepo usuarioRepository;

    private static final String SECRET_KEY = "6LdpRVUrAAAAAMXUbqkpGhxIchH5GeKh4EU8tX_f";

    public AuthRestController(AuthService authService, UsuarioRepo usuarioRepo) {
        super();
        this.authService = authService;
        this.usuarioRepository = usuarioRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        boolean isCaptchaValid = verifyRecaptchaToken(request.getRecaptchaResponse());

        if (!isCaptchaValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Invalid reCAPTCHA"));
        }

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            return ResponseEntity.ok().body("{\"message\": \"Sesión cerrada con éxito.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cerrar la sesión: " + e.getMessage());
        }
    }

    private boolean verifyRecaptchaToken(String recaptchaToken) {
        String verificationUrl = "https://www.google.com/recaptcha/api/siteverify";
        try {
            String urlParameters = "secret=" + SECRET_KEY + "&response=" + recaptchaToken;
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

            URL url = new URL(verificationUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.getOutputStream().write(postData);
            System.out.println("Token recibido para verificación: " + recaptchaToken);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parsear la respuesta JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.toString());

            System.out.println("Respuesta reCAPTCHA: " + rootNode.toPrettyString());

            // Obtener el valor del campo "success"
            boolean success = rootNode.path("success").asBoolean();

            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UsuarioRepo getUsuarioRepository() {
        return usuarioRepository;
    }
}