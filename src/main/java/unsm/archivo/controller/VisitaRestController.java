package unsm.archivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import unsm.archivo.entitys.Visitante;
import unsm.archivo.request.VisitaRequest;
import unsm.archivo.services.VisitaService;

@RestController
@RequestMapping("/visita")
public class VisitaRestController
{
	@Autowired
	VisitaService service;
	
	@PostMapping("/new")
	public void save (@RequestBody VisitaRequest request) 
	{
		service.save(request);
	}
	
	@GetMapping("/view")
    public Page<Visitante> listarVisitantes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) 
	{
        Pageable pageable = PageRequest.of(page, size);
        return service.listarVisitantes(pageable);
    }
}
