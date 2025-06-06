package unsm.archivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unsm.archivo.services.EmailService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/verificacion")
public class VerificacionController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar-codigo")
    public ResponseEntity<Map<String, String>> enviarCodigo(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        
        boolean enviado = emailService.sendVerificationCode(email, code);
        
        Map<String, String> response = new HashMap<>();
        if (enviado) {
            response.put("message", "Código enviado correctamente");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Error al enviar el código");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}