package unsm.archivo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unsm.archivo.DTO.UsuarioDTO;
import unsm.archivo.request.UsuarioRequest;
import unsm.archivo.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuariosController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    public List<UsuarioDTO> verusuarios() {
        return service.verusuarios();
    }

    @GetMapping("/verusuario/{id}")
    public UsuarioDTO verusuario(@PathVariable Integer id) {
        return service.verusuario(id);
    }

    @GetMapping("/verusuarioporusername/{username}")
    public UsuarioDTO findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @PostMapping("/nuevousuario")
    public void nuevousuario(@RequestBody UsuarioRequest request) throws IOException {
        service.nuevousuario(request);
    }
}
