package unsm.archivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import unsm.archivo.DTO.ResolucionDTO;
import unsm.archivo.request.ResolucionRequest;
import unsm.archivo.services.ResolucionService;

@RestController
@RequestMapping("/resolucion")
public class ResolucionController 
{
    @Autowired
    ResolucionService service;

    @GetMapping("/verresolucion")
    public Page<ResolucionDTO> verDocumentos
    		(
    			@RequestParam(defaultValue = "0") int page,
    			@RequestParam(defaultValue = "10") int size
    		) 
    {
    	Pageable pageable = PageRequest.of(page, size);
    	return service.verDocumentos(pageable);
    }

    @GetMapping("/verresolucion/{id}")
    public ResolucionDTO verResolucion(@PathVariable String id)
    {
        return service.verUnDocumento(id);
    }
    
    @PostMapping("/nuevaresolucion")
    public void nuevaResolucion(@ModelAttribute ResolucionRequest request) throws Exception
    {
        service.nuevoDocumento(request);
    }    
}
