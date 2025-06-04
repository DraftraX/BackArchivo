package unsm.archivo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unsm.archivo.request.EmailRequest;
import unsm.archivo.request.LoginRequest;
import unsm.archivo.services.EmailService;
import unsm.archivo.services.UsuarioService;

@RestController
@RequestMapping("/change-password")
public class RestablecerController {
    @Autowired
    EmailService emailService;

    @Autowired
    private UsuarioService userService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @PostMapping("/enviarcorreo")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        request.setMailFrom(mailFrom);
        request.setMailTo(request.getMailTo());
        request.setSubject("Cambio de Contraseña");
        UUID uuid = UUID.randomUUID();
        String codigo = uuid.toString();
        request.setCodigo(codigo);
        emailService.sendEmail(request);
        return new ResponseEntity<String>("Correo con plantilla enviado", HttpStatus.OK);
    }

    @PostMapping("/newpassword")
    public ResponseEntity<String> changePassword(@RequestBody LoginRequest request) {
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());

        boolean isChanged = userService.cambiarContrasena(request.getUsername(), request.getPassword());

        if (isChanged) {
            return new ResponseEntity<>("Contraseña cambiada exitosamente.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al cambiar la contraseña.", HttpStatus.BAD_REQUEST);
        }
    }
}
