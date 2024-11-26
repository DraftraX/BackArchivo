package unsm.archivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unsm.archivo.entitys.Visitante;

public interface VisitaRepo extends JpaRepository<Visitante, Integer>
{

}
