package unsm.archivo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import unsm.archivo.entitys.Resolucion;

public interface ResolucionRepo extends PagingAndSortingRepository<Resolucion, String>
{
	Resolucion findByNrodoc(String nrodoc);
    void save(Resolucion doc);
    List<Resolucion> findAll();
    Optional<Resolucion> findById(String doc);
}