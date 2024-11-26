package unsm.archivo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import unsm.archivo.entitys.Usuario;
import unsm.archivo.entitys.Visitante;
import unsm.archivo.repository.UsuarioRepo;
import unsm.archivo.repository.VisitaRepo;
import unsm.archivo.request.VisitaRequest;

@Service
public class VisitaService 
{
	@Autowired
	VisitaRepo repo;
	
	@Autowired
	UsuarioRepo users;
	
	public void save (VisitaRequest request) 
	{
		Visitante visi = new Visitante();
		visi.setFecha(request.getFecha());
		Usuario user = users.findByUsername(request.getUsername())
						.orElseThrow(()->new RuntimeException("No se encontro al usuario"));
		visi.setIdusuario(user);
		visi.setMotivo(request.getMotivo());
		visi.setNombre(request.getNombre());
		visi.setNumerocorreo(request.getNumerocorreo());
		visi.setOcupacion(request.getOcupacion());
		
		repo.save(visi);
	}
	
	public Page<Visitante> listarVisitantes(Pageable pageable)
	{
	    return repo.findAll(pageable);
	}
	
	public Visitante getid(Integer id) 
	{
		return repo.findById(id)
				.orElseThrow(()->new RuntimeException("No se encontro"));
	}
}
