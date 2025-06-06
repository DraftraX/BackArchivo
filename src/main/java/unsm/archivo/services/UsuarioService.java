package unsm.archivo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import unsm.archivo.DTO.UsuarioDTO;
import unsm.archivo.entitys.Cargo;
import unsm.archivo.entitys.Usuario;
import unsm.archivo.repository.CargoRepo;
import unsm.archivo.repository.UsuarioRepo;
import unsm.archivo.request.UsuarioRequest;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepo repousuario;

	@Autowired
	CargoRepo repocargo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void nuevousuario(UsuarioRequest usuario) throws IOException {
		String encoded = passwordEncoder.encode(usuario.getPassword());

		Usuario nuevousuario = new Usuario();

		nuevousuario.setName(usuario.getName());
		nuevousuario.setLastname(usuario.getLastname());
		nuevousuario.setAddress(usuario.getAddress());
		nuevousuario.setDni(usuario.getDni());
		nuevousuario.setPhone(usuario.getPhone());
		nuevousuario.setPassword(encoded);
		nuevousuario.setUsername(usuario.getUsername());
		nuevousuario.setEstado("Activo");

		Set<Cargo> cargos = new HashSet<>();
		Cargo cargo = repocargo.findById(usuario.getCargoid())
				.orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
		cargos.add(cargo);

		nuevousuario.setCargos(cargos);

		repousuario.save(nuevousuario);
	}

	public List<UsuarioDTO> verusuarios() {
		List<Usuario> usuarios = repousuario.findAll();
		List<UsuarioDTO> usuariosdto = new ArrayList<>();

		for (Usuario user : usuarios) {
			Integer cargoId = null;
			if (user.getCargos() != null && !user.getCargos().isEmpty()) {
				Cargo cargo = user.getCargos().iterator().next();
				cargoId = cargo.getId();
			}

			UsuarioDTO dto = new UsuarioDTO(
					user.getId(),
					user.getName(),
					user.getLastname(),
					user.getAddress(),
					user.getPhone(),
					cargoId);

			usuariosdto.add(dto);
		}

		return usuariosdto;
	}

	public UsuarioDTO verusuario(Integer id) {
		Usuario user = repousuario.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Integer cargoId = null;
		if (user.getCargos() != null && !user.getCargos().isEmpty()) {
			Cargo cargo = user.getCargos().iterator().next();
			cargoId = cargo.getId();
		}

		return new UsuarioDTO(
				user.getId(),
				user.getName(),
				user.getLastname(),
				user.getAddress(),
				user.getPhone(),
				cargoId);
	}

	public UsuarioDTO findByUsername(String username) {
		Usuario user = repousuario.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("No se encontró al usuario"));

		Integer cargoId = null;
		if (user.getCargos() != null && !user.getCargos().isEmpty()) {
			Cargo cargo = user.getCargos().iterator().next();
			cargoId = cargo.getId();
		}

		return new UsuarioDTO(
				user.getId(),
				user.getName(),
				user.getLastname(),
				user.getAddress(),
				user.getPhone(),
				cargoId);
	}

	public boolean cambiarContrasena(String username, String newPassword) {
		Usuario user = repousuario.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("No se encontró al usuario"));
		if (user != null) {
			user.setPassword(passwordEncoder.encode(newPassword));
			repousuario.save(user);
			return true;
		}

		return false;
	}

	public void actualizarCargo(Integer usuarioId, Integer cargoId) {
		Usuario usuario = repousuario.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Cargo cargo = repocargo.findById(cargoId)
				.orElseThrow(() -> new RuntimeException("Cargo no encontrado"));

		Set<Cargo> cargos = new HashSet<>();
		cargos.add(cargo);

		usuario.setCargos(cargos);

		repousuario.save(usuario);
	}
}
