package unsm.archivo.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import unsm.archivo.DTO.GradoTituloDTO;
import unsm.archivo.entitys.GradoTitulo;
import unsm.archivo.entitys.Resolucion;
import unsm.archivo.repository.GradoTituloRepo;
import unsm.archivo.repository.ResolucionRepo;
import unsm.archivo.request.GradoTituloRequest;

@Service
public class PregradoService {
    
    @Autowired
    GradoTituloRepo gradorepo;

    @Autowired
    ResolucionRepo resolucionrepo;

    public void nuevoDocumento(GradoTituloRequest request) throws IOException {
        GradoTitulo grado = new GradoTitulo();
        grado.setNombreapellido(request.getNombreapellido());
        grado.setDni(request.getDni());
        grado.setFacultadescuela(request.getFacultadescuela()); 
        grado.setGradotitulo(request.getGradotitulo()); 
        
        Resolucion resolucion = resolucionrepo.findById(request.getIdresolucion())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Resolucion Id: " + request.getIdresolucion()));
        grado.setIdresolucion(resolucion);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (request.getFechaexpedicion() != null) 
        {
            LocalDate fecha = LocalDate.parse(request.getFechaexpedicion(), formatter);
            grado.setFechaexpedicion(fecha);
        } 
        
        else 
        {
            throw new IllegalArgumentException("Fecha no puede ser nula");
        }
        
        MultipartFile pdfFile = request.getPdf();
        if (pdfFile != null && !pdfFile.isEmpty()) {
            grado.setPdf(pdfFile.getBytes()); 
        }

        gradorepo.save(grado); 
    }
    
    public Page<GradoTituloDTO> verDocumentos(Pageable pageable) {
        Page<GradoTitulo> documentos = gradorepo.findAll(pageable);
        return documentos.map(documento -> {
            GradoTituloDTO documentoDTO = new GradoTituloDTO();
            documentoDTO.setIdgradotitulo(documento.getIdgradotitulo());
            documentoDTO.setNombreapellido(documento.getNombreapellido());
            documentoDTO.setDni(documento.getDni());
            documentoDTO.setFechaexpedicion(documento.getFechaexpedicion().toString());
            documentoDTO.setFacultadescuela(documento.getFacultadescuela());
            documentoDTO.setGradotitulo(documento.getGradotitulo());
            documentoDTO.setIdresolucion(documento.getIdresolucion().getNrodoc());
            return documentoDTO;
        });
    }

    public GradoTituloDTO verUnDocumento(Integer idgradotitulo) {
        GradoTitulo documento = gradorepo.findById(idgradotitulo)
                .orElseThrow(() -> new RuntimeException("No se encontró el documento"));

        GradoTituloDTO documentoDTO = new GradoTituloDTO();
        documentoDTO.setIdgradotitulo(documento.getIdgradotitulo()); 
        documentoDTO.setNombreapellido(documento.getNombreapellido()); 
        documentoDTO.setDni(documento.getDni());
        documentoDTO.setFechaexpedicion(documento.getFechaexpedicion().toString());
        documentoDTO.setFacultadescuela(documento.getFacultadescuela()); 
        documentoDTO.setGradotitulo(documento.getGradotitulo()); 
        documentoDTO.setIdresolucion(documento.getIdresolucion().getNrodoc());

        return documentoDTO;
    }

    public GradoTitulo verDocumentoPdf(Integer idgradotitulo) {
        GradoTitulo documento = gradorepo.findById(idgradotitulo)
                .orElseThrow(() -> new RuntimeException("No se encontró el documento"));

        return documento;
    }

    public List<GradoTituloDTO> verDocumentosPorResolucion(String idresolucion) {
        Resolucion resolucion = resolucionrepo.findByNrodoc(idresolucion);

        List<GradoTitulo> documentos = gradorepo.findByIdresolucion(resolucion);
        List<GradoTituloDTO> documentosdto = new ArrayList<>();

        for (GradoTitulo documento : documentos) {
            GradoTituloDTO documentoDTO = new GradoTituloDTO();
            documentoDTO.setIdgradotitulo(documento.getIdgradotitulo()); 
            documentoDTO.setNombreapellido(documento.getNombreapellido()); 
            documentoDTO.setDni(documento.getDni());
            documentoDTO.setFechaexpedicion(documento.getFechaexpedicion().toString());
            documentoDTO.setFacultadescuela(documento.getFacultadescuela()); 
            documentoDTO.setGradotitulo(documento.getGradotitulo());
            documentoDTO.setIdresolucion(documento.getIdresolucion().getNrodoc());

            documentosdto.add(documentoDTO);
        }
        return documentosdto;
    }
}
