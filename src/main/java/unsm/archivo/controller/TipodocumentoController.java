package unsm.archivo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unsm.archivo.entitys.Tipocriterio;
import unsm.archivo.request.TipocriterioRequest;
import unsm.archivo.services.TipocriterioService;

@RestController
@RequestMapping("/tipodocumento")
public class TipodocumentoController
{
    @Autowired
    TipocriterioService tipocriterioService;
    
    @GetMapping("/tipocriterios")
    public List<Tipocriterio> getTipocriterios()
    {
        return tipocriterioService.getTipocriterios();
    }

    @GetMapping("/tipocriterio")
    public Tipocriterio VerUnTipoCriterio(Integer id)
    {
        return tipocriterioService.VerUnTipoCriterio(id);
    }

    @PostMapping("/nuevocriterio")
    public void nuevoCriterio(TipocriterioRequest tipocriterio)
    {
        tipocriterioService.nuevoTipocriterio(tipocriterio);
    }
}
