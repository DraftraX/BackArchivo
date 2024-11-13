package unsm.archivo.entitys;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RR 
{
	@Id
	String nrodoc;
	
	@Column
	String titulo;
	String estado;
	LocalDate fecha;
	Integer duracion;
	LocalDate vencimiento;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name= "idtipocriterio")
	Tipocriterio idtipocriterio;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name= "idusuario")
	Usuario idusuario;
}
