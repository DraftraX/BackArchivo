package unsm.archivo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import unsm.archivo.DTO.GradoTituloDTO;
import unsm.archivo.entitys.GradoTitulo;
import unsm.archivo.request.GradoTituloRequest;
import unsm.archivo.services.PregradoService;

@RestController
@RequestMapping("/gradotitulos")
public class PregradoController 
{
    @Autowired
    PregradoService service;

    @GetMapping("/vergradotitulo")
    public Page<GradoTituloDTO> verGradoTitulos(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return service.verDocumentos(pageable);
    }

    @GetMapping("/vergradotitulo/{id}")
    public GradoTituloDTO verGradoTitulo(@PathVariable Integer id) {
        return service.verUnDocumento(id);
    }

    @GetMapping("/vergradotitulo/{id}/pdf")
    public ResponseEntity<byte[]> verGradoTituloPdf(@PathVariable Integer id) {
        GradoTitulo documento = service.verDocumentoPdf(id);
        byte[] pdfContent = documento.getPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", documento.getIdgradotitulo() + ".pdf");
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }

    @GetMapping("/vergradotitulo/resolucion/{id}")
    public List<GradoTituloDTO> verGradoTitulosPorResolucion(@PathVariable String id) {
        return service.verDocumentosPorResolucion(id);
    }

    @PostMapping("/nuevogradotitulo")
    public void nuevoGradoTitulo(@ModelAttribute GradoTituloRequest request) throws IOException {
        service.nuevoDocumento(request);
    }
}

