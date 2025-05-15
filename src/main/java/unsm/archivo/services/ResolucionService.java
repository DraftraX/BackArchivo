package unsm.archivo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import unsm.archivo.DTO.ResolucionDTO;
import unsm.archivo.entitys.Resolucion;
import unsm.archivo.entitys.Usuario;
import unsm.archivo.repository.ResolucionRepo;
import unsm.archivo.repository.UsuarioRepo;
import unsm.archivo.request.ResolucionRequest;

@Service
public class ResolucionService 
{
    @Autowired
    ResolucionRepo documentorepo;
    
    @Autowired
    UsuarioRepo usuario;
    
    @Autowired
    private EncryptionService encryption;

    public void nuevoDocumento(ResolucionRequest documentosRequest) throws Exception 
    {
        Resolucion doc = new Resolucion();
        doc.setNrodoc(documentosRequest.getNrodoc());
        doc.setTitulo(documentosRequest.getTitulo());
        doc.setEstado(documentosRequest.getEstado());
        doc.setLink(encryption.encrypt(documentosRequest.getLink()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (documentosRequest.getFecha() != null) {
            LocalDate fecha = LocalDate.parse(documentosRequest.getFecha(), formatter);
            doc.setFecha(fecha);
            
            if (documentosRequest.getTipoResolucion().equals("Temporal")) {
                int duracion = documentosRequest.getDuracion();
                doc.setDuracion(duracion);
                LocalDate fechaVencimiento = fecha.plus(duracion, ChronoUnit.YEARS);
                doc.setVencimiento(fechaVencimiento);
            }
        } 
        else 
        {
            throw new IllegalArgumentException("Fecha no puede ser nula");
        }
        
        Usuario usuer = usuario.findByUsername(documentosRequest.getUsuario())
        		.orElseThrow(()-> new IllegalArgumentException("Invalid Criterio Id:" + documentosRequest.getUsuario()));
        doc.setIdusuario(usuer);

        documentorepo.save(doc);
    }
    
    public Page<ResolucionDTO> verDocumentos(Pageable pageable)
    {
	    Page<Resolucion> documentos = documentorepo.findAll(pageable);
	    return documentos.map(documento -> 
	    {
	        ResolucionDTO documentoDTO = new ResolucionDTO();
	        documentoDTO.setNrodoc(documento.getNrodoc());
	        documentoDTO.setTitulo(documento.getTitulo());
	        documentoDTO.setEstado(documento.getEstado());
	        documentoDTO.setFecha(documento.getFecha().toString());
	        try
	        {
				documentoDTO.setLink(encryption.decrypt(documento.getLink()));
			} 
	        catch (Exception e) 
	        {
				e.printStackTrace();
			}
	
	        if (documento.getDuracion() != null) 
	        {
	            documentoDTO.setDuracion(documento.getDuracion());
	        } else {
	            documentoDTO.setDuracion(0);
	        }
	
	        if (documento.getVencimiento() != null) {
	            documentoDTO.setVencimiento(documento.getVencimiento().toString());
	        } else {
	            documentoDTO.setVencimiento("Permanente");
	        }
	
	        return documentoDTO;
    	});
    }

    public ResolucionDTO verUnDocumento(String doc) 
    {
    	Resolucion documento = documentorepo.findById(doc)
                .orElseThrow(() -> new RuntimeException("No se encontro el documento"));

        ResolucionDTO documentoDTO = new ResolucionDTO();
        documentoDTO.setNrodoc(documento.getNrodoc());
        documentoDTO.setTitulo(documento.getTitulo());
        documentoDTO.setEstado(documento.getEstado());
        documentoDTO.setFecha(documento.getFecha().toString());
        
        try
        {
			documentoDTO.setLink(encryption.decrypt(documento.getLink()));
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
        
        if (documento.getDuracion() != null) {
            documentoDTO.setDuracion(documento.getDuracion());
        } else {
            documentoDTO.setDuracion(0);
        }
        
        if (documento.getVencimiento() != null) {
            documentoDTO.setVencimiento(documento.getVencimiento().toString());
        } else {
            documentoDTO.setVencimiento("Permanente");
        }
        
        return documentoDTO;
    }   
}