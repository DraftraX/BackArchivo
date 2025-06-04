// package unsm.archivo.config;

// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import unsm.archivo.entitys.Cargo;
// import unsm.archivo.entitys.Usuario;
// import unsm.archivo.repository.CargoRepo;
// import unsm.archivo.repository.UsuarioRepo;

// @Configuration
// public class DataInitializer {

// @Bean
// CommandLineRunner initializeData(UsuarioRepo usuarioRepository,
// PasswordEncoder passwordEncoder,
// CargoRepo rolRepository) {
// return args -> {
// Cargo administrador = new Cargo();
// administrador.setName("ADMINISTRADOR");
// rolRepository.save(administrador);

// Usuario usuario = new Usuario();
// Set<Cargo> cargos = new HashSet<>();
// Cargo cargo = rolRepository.findByName("ADMINISTRADOR")
// .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
// cargos.add(cargo);
// usuario.setUsername("eduysting@gmail.com");
// usuario.setEstado("Activo");
// usuario.setDni("12345678");
// usuario.setName("Eduardo Abel");
// usuario.setLastname("Padilla Coral");
// usuario.setAddress(" Jirón José Carlos Mariategui n° 123");
// usuario.setPhone("123456789");
// usuario.setPassword(passwordEncoder.encode("123456"));
// usuario.setCargos(cargos);
// usuarioRepository.save(usuario);
// };
// }
// }

// MAS ROLES Y USUARIOS
// package unsm.archivo.config;

// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import unsm.archivo.entitys.Cargo;
// import unsm.archivo.entitys.Usuario;
// import unsm.archivo.repository.CargoRepo;
// import unsm.archivo.repository.UsuarioRepo;

// @Configuration
// public class DataInitializer {

// @Bean
// CommandLineRunner initializeData(UsuarioRepo usuarioRepository,
// PasswordEncoder passwordEncoder,
// CargoRepo cargoRepository) {
// return args -> {

// // Crear cargos
// Cargo administrador = new Cargo();
// administrador.setName("ADMINISTRADOR");
// cargoRepository.save(administrador);

// Cargo jefeArchivo = new Cargo();
// jefeArchivo.setName("JEFE ARCHIVO");
// cargoRepository.save(jefeArchivo);

// Cargo secretaria = new Cargo();
// secretaria.setName("SECRETARIA");
// cargoRepository.save(secretaria);

// Cargo usuarioCargo = new Cargo();
// usuarioCargo.setName("USUARIO");
// cargoRepository.save(usuarioCargo);

// // Crear usuario ADMINISTRADOR
// Usuario usuarioAdmin = new Usuario();
// Set<Cargo> cargosAdmin = new HashSet<>();
// Cargo cargoAdmin = cargoRepository.findByName("ADMINISTRADOR")
// .orElseThrow(() -> new RuntimeException("Cargo ADMINISTRADOR no
// encontrado"));
// cargosAdmin.add(cargoAdmin);

// usuarioAdmin.setUsername("eduysting@gmail.com");
// usuarioAdmin.setEstado("Activo");
// usuarioAdmin.setDni("12345678");
// usuarioAdmin.setName("Eduardo Abel");
// usuarioAdmin.setLastname("Padilla Coral");
// usuarioAdmin.setAddress("Jirón José Carlos Mariategui n° 123");
// usuarioAdmin.setPhone("123456789");
// usuarioAdmin.setPassword(passwordEncoder.encode("123456"));
// usuarioAdmin.setCargos(cargosAdmin);
// usuarioRepository.save(usuarioAdmin);

// // Crear usuario JEFE ARCHIVO
// Usuario usuarioJefeArchivo = new Usuario();
// Set<Cargo> cargosJefe = new HashSet<>();
// Cargo cargoJefe = cargoRepository.findByName("JEFE ARCHIVO")
// .orElseThrow(() -> new RuntimeException("Cargo JEFE ARCHIVO no encontrado"));
// cargosJefe.add(cargoJefe);

// usuarioJefeArchivo.setUsername("wmanuelga@gmail.com");
// usuarioJefeArchivo.setEstado("Activo");
// usuarioJefeArchivo.setDni("72809055");
// usuarioJefeArchivo.setName("Wilmar Manuel");
// usuarioJefeArchivo.setLastname("Gomez Avalos");
// usuarioJefeArchivo.setAddress("Jr. 1 de Mayor 610 Morales");
// usuarioJefeArchivo.setPhone("941579343");
// usuarioJefeArchivo.setPassword(passwordEncoder.encode("123456"));
// usuarioJefeArchivo.setCargos(cargosJefe);
// usuarioRepository.save(usuarioJefeArchivo);

// // Crear usuario SECRETARIA
// Usuario usuarioSecretaria = new Usuario();
// Set<Cargo> cargosSecretaria = new HashSet<>();
// Cargo cargoSecretaria = cargoRepository.findByName("SECRETARIA")
// .orElseThrow(() -> new RuntimeException("Cargo SECRETARIA no encontrado"));
// cargosSecretaria.add(cargoSecretaria);

// usuarioSecretaria.setUsername("samir@gmail.com");
// usuarioSecretaria.setEstado("Activo");
// usuarioSecretaria.setDni("12345678");
// usuarioSecretaria.setName("Paul Samir");
// usuarioSecretaria.setLastname("Vidaurre Gonzales");
// usuarioSecretaria.setAddress("WIKUGOXDXDXD");
// usuarioSecretaria.setPhone("123456789");
// usuarioSecretaria.setPassword(passwordEncoder.encode("123456"));
// usuarioSecretaria.setCargos(cargosSecretaria);
// usuarioRepository.save(usuarioSecretaria);

// // Crear usuario USUARIO
// Usuario usuarioComun = new Usuario();
// Set<Cargo> cargosUsuarioComun = new HashSet<>();
// Cargo cargoUsuario = cargoRepository.findByName("USUARIO")
// .orElseThrow(() -> new RuntimeException("Cargo USUARIO no encontrado"));
// cargosUsuarioComun.add(cargoUsuario);

// usuarioComun.setUsername("ismael@gmail.com");
// usuarioComun.setEstado("Activo");
// usuarioComun.setDni("12345678");
// usuarioComun.setName("Ismael Nehemias");
// usuarioComun.setLastname("Haro Carrasco");
// usuarioComun.setAddress("POLVORAXD");
// usuarioComun.setPhone("111111111");
// usuarioComun.setPassword(passwordEncoder.encode("123456"));
// usuarioComun.setCargos(cargosUsuarioComun);
// usuarioRepository.save(usuarioComun);

// };
// }
// }
