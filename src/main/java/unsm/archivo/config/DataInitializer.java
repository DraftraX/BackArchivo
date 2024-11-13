/*package unsm.archivo.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import unsm.archivo.entitys.Cargo;
import unsm.archivo.entitys.Usuario;
import unsm.archivo.repository.CargoRepo;
import unsm.archivo.repository.UsuarioRepo;

@Configuration
public class DataInitializer 
{
	
    @Bean
    CommandLineRunner initializeData(UsuarioRepo usuarioRepository, PasswordEncoder passwordEncoder, CargoRepo rolRepository) 
    {
        return args ->
        {
            Cargo administrador = new Cargo();
            administrador.setName("ADMINISTRADOR");
            rolRepository.save(administrador);
            
            Usuario usuario = new Usuario();
            Set<Cargo> cargos = new HashSet<>();
    		Cargo cargo = rolRepository.findByName("ADMINISTRADOR").orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
    		cargos.add(cargo);
            usuario.setUsername("eduysting@gmail.com");
            usuario.setEstado("Activo");
            usuario.setDni("12345678");
            usuario.setName("Eduardo Abel");
            usuario.setLastname("Padilla Coral");
            usuario.setAddress(" Jirón José Carlos Mariategui n° 123");
            usuario.setPhone("123456789");
            usuario.setPassword(passwordEncoder.encode("123456"));
            usuario.setCargos(cargos);
            usuarioRepository.save(usuario);
        };
    }
}*/